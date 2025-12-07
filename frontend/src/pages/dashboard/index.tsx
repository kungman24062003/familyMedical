import React, { useEffect, useState } from "react";
import {
  Card,
  Spin,
  Row,
  Col,
  Badge,
  Button,
  FloatButton,
  Modal,
  Input,
} from "antd";
import {
  RobotOutlined,
  UsergroupAddOutlined,
  FileTextOutlined,
} from "@ant-design/icons";
import { useAppDispatch, useAppSelector } from "../../redux/hooks";
import { fetchAppointments } from "../../redux/slice/appointmentSlice";
import dayjs, { Dayjs } from "dayjs";

const DashboardPage: React.FC = () => {
  const dispatch = useAppDispatch();
  const { list: appointments, loading } = useAppSelector(
    (s) => s.appointment
  );

  const [chatbotOpen, setChatbotOpen] = useState(false);
  const [messages, setMessages] = useState<
    Array<{ type: "user" | "bot"; content: string; severity?: string; requiresDoctor?: boolean }>
  >([
    {
      type: "bot",
      content:
        "Xin chào! Tôi là trợ lý ảo của HSSK. Tôi có thể giúp gì cho bạn?",
    },
  ]);
  const [inputMessage, setInputMessage] = useState("");
  const [isSending, setIsSending] = useState(false);

  useEffect(() => {
    dispatch(fetchAppointments());
  }, [dispatch]);

  const handleSendMessage = async () => {
    if (!inputMessage.trim()) return;

    const userMsg = inputMessage.trim();
    setMessages((prev) => [...prev, { type: "user", content: userMsg }]);
    setInputMessage("");
    setIsSending(true);

    try {
      const response = await fetch(
        "http://localhost:8080/familyhealth/api/v1/ai/consult",
        {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${localStorage.getItem("access_token") || ""}`,
          },
          body: JSON.stringify({ symptoms: userMsg }),
        }
      );

      if (!response.ok) throw new Error("API call failed");

      const data = await response.json();

      if (data.code === 200 && data.data) {
        setMessages((prev) => [
          ...prev,
          {
            type: "bot",
            content: data.data.advice,
            severity: data.data.severity,
            requiresDoctor: data.data.requiresDoctor,
          },
        ]);
      } else {
        throw new Error("Invalid response");
      }
    } catch (error) {
      console.error("Chatbot error:", error);
      setMessages((prev) => [
        ...prev,
        {
          type: "bot",
          content:
            "Xin lỗi, tôi đang gặp sự cố kết nối. Vui lòng thử lại sau hoặc liên hệ trực tiếp với bác sĩ nếu khẩn cấp.",
        },
      ]);
    } finally {
      setIsSending(false);
    }
  };

  if (loading) {
    return (
      <div style={{ textAlign: "center", padding: "100px 0" }}>
        <Spin size="large" />
      </div>
    );
  }

  return (
    <div>
      {/* Header Card */}
      <Card
        style={{
          marginBottom: 16,
          textAlign: "center",
          backgroundColor: "#f0f5ff",
          borderRadius: 12,
        }}
      >
        <h1 style={{ fontSize: 32, marginBottom: 12, color: "#1d39c4" }}>
          Chào mừng bạn đến với HSSK!
        </h1>
        <p style={{ fontSize: 18, color: "#333" }}>
          Ở đây bạn có thể quản lý sức khỏe gia đình một cách dễ dàng:
        </p>

        <Row gutter={[16, 16]} justify="center" style={{ marginTop: 24 }}>
          <Col xs={24} sm={8}>
            <Card
              hoverable
              style={{ backgroundColor: "#ffd666", textAlign: "center", borderRadius: 12 }}
            >
              <UsergroupAddOutlined style={{ fontSize: 48, color: "#ad4e00" }} />
              <h2 style={{ marginTop: 12, color: "#ad4e00" }}>
                Thêm / Quản lý hộ gia đình
              </h2>
            </Card>
          </Col>
          <Col xs={24} sm={8}>
            <Card
              hoverable
              style={{ backgroundColor: "#95de64", textAlign: "center", borderRadius: 12 }}
            >
              <FileTextOutlined style={{ fontSize: 48, color: "#237804" }} />
              <h2 style={{ marginTop: 12, color: "#237804" }}>Xem kết quả khám bệnh</h2>
            </Card>
          </Col>
          <Col xs={24} sm={8}>
            <Card
              hoverable
              style={{ backgroundColor: "#69c0ff", textAlign: "center", borderRadius: 12 }}
            >
              <RobotOutlined style={{ fontSize: 48, color: "#096dd9" }} />
              <h2 style={{ marginTop: 12, color: "#096dd9" }}>Trò chuyện với trợ lý ảo</h2>
            </Card>
          </Col>
        </Row>
      </Card>

      {/* Chatbot FloatButton */}
      <FloatButton
        icon={<RobotOutlined style={{ fontSize: 24 }} />}
        type="primary"
        style={{ right: 24, bottom: 24, width: 60, height: 60 }}
        onClick={() => setChatbotOpen(true)}
        tooltip="Trợ lý ảo"
      />

      {/* Chatbot Modal */}
      <Modal
        title={
          <div style={{ display: "flex", alignItems: "center", gap: 8 }}>
            <RobotOutlined style={{ fontSize: 20, color: "#1890ff" }} />
            <span>Trợ lý ảo HSSK</span>
          </div>
        }
        open={chatbotOpen}
        onCancel={() => setChatbotOpen(false)}
        footer={null}
        width={650}
        bodyStyle={{ padding: 0 }}
      >
        <div style={{ display: "flex", flexDirection: "column", height: 600 }}>
          {/* Messages Area */}
          <div
            style={{
              flex: 1,
              overflowY: "auto",
              padding: 16,
              backgroundColor: "#f5f5f5",
            }}
          >
            {messages.map((msg, idx) => (
              <div key={idx}>
                <div
                  style={{
                    display: "flex",
                    justifyContent: msg.type === "user" ? "flex-end" : "flex-start",
                    marginBottom: 12,
                  }}
                >
                  <div
                    style={{
                      maxWidth: "70%",
                      padding: "8px 12px",
                      borderRadius: 8,
                      backgroundColor: msg.type === "user" ? "#1890ff" : "#fff",
                      color: msg.type === "user" ? "#fff" : "#000",
                      boxShadow: "0 1px 2px rgba(0,0,0,0.1)",
                      whiteSpace: "pre-line",
                    }}
                  >
                    {msg.content}
                  </div>
                </div>

                {/* Severity warning */}
                {msg.severity === "HIGH" && msg.requiresDoctor && (
                  <div
                    style={{
                      marginBottom: 12,
                      padding: 12,
                      backgroundColor: "#fff2e8",
                      border: "1px solid #ffbb96",
                      borderRadius: 8,
                    }}
                  >
                    <div
                      style={{
                        color: "#d4380d",
                        fontWeight: 600,
                        marginBottom: 4,
                        display: "flex",
                        alignItems: "center",
                        gap: 4,
                      }}
                    >
                      ⚠️ Cảnh báo khẩn cấp
                    </div>
                    <div style={{ fontSize: 12, color: "#ad4e00" }}>
                      Triệu chứng của bạn có thể nghiêm trọng. Vui lòng đặt lịch khám
                      ngay hoặc đến cơ sở y tế gần nhất.
                    </div>
                  </div>
                )}
              </div>
            ))}
            {isSending && (
              <div style={{ display: "flex", justifyContent: "flex-start", marginBottom: 12 }}>
                <div
                  style={{
                    padding: "8px 12px",
                    borderRadius: 8,
                    backgroundColor: "#fff",
                    boxShadow: "0 1px 2px rgba(0,0,0,0.1)",
                  }}
                >
                  <Spin size="small" /> Đang trả lời...
                </div>
              </div>
            )}
          </div>

          {/* Input Area */}
          <div
            style={{
              padding: 16,
              borderTop: "1px solid #f0f0f0",
              backgroundColor: "#fff",
            }}
          >
            <Input
              placeholder="Nhập câu hỏi của bạn..."
              value={inputMessage}
              onChange={(e) => setInputMessage(e.target.value)}
              onPressEnter={handleSendMessage}
              suffix={
                <Button
                  type="primary"
                  onClick={handleSendMessage}
                  disabled={!inputMessage.trim() || isSending}
                  size="small"
                >
                  Gửi
                </Button>
              }
              disabled={isSending}
            />
          </div>
        </div>
      </Modal>
    </div>
  );
};

export default DashboardPage;