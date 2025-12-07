import React, { useState } from "react";
import {
  Form,
  Input,
  Button,
  DatePicker,
  Select,
  Row,
  Col,
  Typography,
  App as AntApp,
} from "antd";
import {
  UserOutlined,
  LockOutlined,
  PhoneOutlined,
  IdcardOutlined,
  HomeOutlined,
} from "@ant-design/icons";
import logo from "../../assets/logo.png";
import { useNavigate } from "react-router-dom";
import axiosInstance from "../../config/axios";
import dayjs from "dayjs";

const { Title, Text } = Typography;

const Register: React.FC = () => {
  const navigate = useNavigate();
  const { message } = AntApp.useApp();
  const [loading, setLoading] = useState(false);
  const [form] = Form.useForm();

  const onFinish = async (values: any) => {
    setLoading(true);
    try {
      const payload = {
        phone: values.phone,
        password: values.password,
        roleId: 4,
        isActive: true,
        memberInfo: {
          fullName: values.fullName,
          address: values.address,
          gender: values.gender,
          dateOfBirth: values.dateOfBirth.format("YYYY-MM-DD"),
          cccd: values.cccd,
          bhyt: values.bhyt || null,
        },
      };

      await axiosInstance.post("/users/register", payload);

      message.success("🎉 Đăng ký thành công!");
      setTimeout(() => navigate("/login"), 1200);
    } catch (err: any) {
      let errorMsg = err?.response?.data?.message || "Đăng ký thất bại";
      message.error(errorMsg);
    } finally {
      setLoading(false);
    }
  };

  return (
    <AntApp>
      <div
        style={{
          minHeight: "100vh",
          display: "flex",
          justifyContent: "center",
          alignItems: "center",
          padding: 20,
          background:
            "linear-gradient(135deg, #0ea5e9 0%, #2563eb 50%, #1e40af 100%)",
        }}
      >
        {/* GLASS CARD */}
        <div
          style={{
            width: "100%",
            maxWidth: 750,
            padding: "45px 40px",
            background: "rgba(255, 255, 255, 0.15)",
            borderRadius: 20,
            boxShadow: "0 20px 60px rgba(0,0,0,0.25)",
            backdropFilter: "blur(15px)",
            border: "1px solid rgba(255,255,255,0.2)",
          }}
        >
          {/* Logo & Title */}
          <div style={{ textAlign: "center", marginBottom: 30 }}>
            <img
              src={logo}
              alt="Logo"
              style={{ width: 100, height: "auto"}}
            />
            <Title level={2} style={{ color: "white", marginBottom: 0 }}>
              FamilyMedicalRecords
            </Title>
          </div>

          {/* FORM */}
          <Form
            form={form}
            layout="vertical"
            onFinish={onFinish}
            requiredMark={false}
            size="large"
          >

            <Row gutter={16}>
              <Col span={12}>
                <Form.Item
                  label={<span style={{ color: "white" }}>Số điện thoại</span>}
                  name="phone"
                  rules={[
                    { required: true, message: "Nhập SĐT" },
                    { pattern: /^[0-9]{10}$/, message: "SĐT gồm 10 số" },
                  ]}
                >
                  <Input
                    prefix={<PhoneOutlined style={{ color: "#e0e7ff" }} />}
                    placeholder="0987654321"
                    style={{
                      height: 50,
                      borderRadius: 10,
                      background: "rgba(255,255,255,0.25)",
                      color: "white",
                    }}
                  />
                </Form.Item>
              </Col>

              <Col span={12}>
                <Form.Item
                  label={<span style={{ color: "white" }}>Mật khẩu</span>}
                  name="password"
                  rules={[
                    { required: true, message: "Nhập mật khẩu" },
                    { min: 6, message: "Tối thiểu 6 ký tự" },
                  ]}
                >
                  <Input.Password
                    prefix={<LockOutlined style={{ color: "#e0e7ff" }} />}
                    placeholder="Nhập mật khẩu"
                    style={{
                      height: 50,
                      borderRadius: 10,
                      background: "rgba(255,255,255,0.25)",
                      color: "white",
                    }}
                  />
                </Form.Item>
              </Col>
            </Row>

            <Form.Item
              label={<span style={{ color: "white" }}>Xác nhận mật khẩu</span>}
              name="confirmPassword"
              dependencies={["password"]}
              rules={[
                { required: true, message: "Nhập lại mật khẩu" },
                ({ getFieldValue }) => ({
                  validator(_, value) {
                    if (!value || getFieldValue("password") === value)
                      return Promise.resolve();
                    return Promise.reject("Mật khẩu không khớp");
                  },
                }),
              ]}
            >
              <Input.Password
                prefix={<LockOutlined style={{ color: "#e0e7ff" }} />}
                placeholder="Nhập lại mật khẩu"
                style={{
                  height: 50,
                  borderRadius: 10,
                  background: "rgba(255,255,255,0.25)",
                  color: "white",
                }}
              />
            </Form.Item>

            <Form.Item
              label={<span style={{ color: "white" }}>Họ và tên</span>}
              name="fullName"
              rules={[{ required: true, message: "Nhập họ tên" }]}
            >
              <Input
                prefix={<UserOutlined style={{ color: "#e0e7ff" }} />}
                placeholder="Nguyễn Văn A"
                style={{
                  height: 50,
                  borderRadius: 10,
                  background: "rgba(255,255,255,0.25)",
                  color: "white",
                }}
              />
            </Form.Item>

            <Row gutter={16}>
              <Col span={12}>
                <Form.Item
                  label={<span style={{ color: "white" }}>Giới tính</span>}
                  name="gender"
                  rules={[{ required: true, message: "Chọn giới tính" }]}
                >
                  <Select
                    placeholder="Chọn giới tính"
                    style={{
                      height: 50,
                      borderRadius: 10,
                    }}
                    options={[
                      { value: "MALE", label: "Nam" },
                      { value: "FEMALE", label: "Nữ" },
                      { value: "OTHER", label: "Khác" },
                    ]}
                  />
                </Form.Item>
              </Col>

              <Col span={12}>
                <Form.Item
                  label={<span style={{ color: "white" }}>Ngày sinh</span>}
                  name="dateOfBirth"
                  rules={[{ required: true, message: "Chọn ngày sinh" }]}
                >
                  <DatePicker
                    style={{
                      width: "100%",
                      height: 50,
                      borderRadius: 10,
                    }}
                    format="DD/MM/YYYY"
                    disabledDate={(d) => d && d > dayjs()}
                  />
                </Form.Item>
              </Col>
            </Row>

            <Form.Item
              label={<span style={{ color: "white" }}>Địa chỉ</span>}
              name="address"
              rules={[{ required: true, message: "Nhập địa chỉ" }]}
            >
              <Input.TextArea
                rows={3}
                placeholder="Số nhà, đường, phường/xã, quận/huyện..."
                style={{
                  borderRadius: 10,
                  background: "rgba(255,255,255,0.25)",
                  color: "white",
                }}
              />
            </Form.Item>

            <Row gutter={16}>
              <Col span={12}>
                <Form.Item
                  label={<span style={{ color: "white" }}>CCCD/CMND</span>}
                  name="cccd"
                  rules={[
                    { required: true, message: "Nhập CCCD" },
                    { pattern: /^[0-9]{9,12}$/, message: "9–12 số" },
                  ]}
                >
                  <Input
                    prefix={<IdcardOutlined style={{ color: "#e0e7ff" }} />}
                    placeholder="0123456789"
                    style={{
                      height: 50,
                      borderRadius: 10,
                      background: "rgba(255,255,255,0.25)",
                      color: "white",
                    }}
                  />
                </Form.Item>
              </Col>

              <Col span={12}>
                <Form.Item
                  label={<span style={{ color: "white" }}>Số BHYT (tùy chọn)</span>}
                  name="bhyt"
                >
                  <Input
                    prefix={<HomeOutlined style={{ color: "#e0e7ff" }} />}
                    placeholder="066204001282"
                    style={{
                      height: 50,
                      borderRadius: 10,
                      background: "rgba(255,255,255,0.25)",
                      color: "white",
                    }}
                  />
                </Form.Item>
              </Col>
            </Row>

            <Button
              type="primary"
              htmlType="submit"
              block
              loading={loading}
              style={{
                height: 52,
                borderRadius: 12,
                fontSize: 16,
                fontWeight: 600,
                marginTop: 10,
                background: "linear-gradient(135deg, #3b82f6, #06b6d4)",
                border: "none",
              }}
            >
              Đăng ký ngay
            </Button>
          </Form>

          {/* LOGIN LINK */}
          <div style={{ textAlign: "center", marginTop: 20 }}>
            <Text style={{ color: "#e0e7ff" }}>
              Đã có tài khoản?{" "}
              <a
                href="/login"
                style={{
                  color: "white",
                  fontWeight: "bold",
                  textDecoration: "underline",
                }}
              >
                Đăng nhập
              </a>
            </Text>
          </div>
        </div>
      </div>
    </AntApp>
  );
};

export default Register;
