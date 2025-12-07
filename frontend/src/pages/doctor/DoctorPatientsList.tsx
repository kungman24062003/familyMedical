import React, { useState, useEffect } from "react";
import { Table, Button, Space, Tag, Card, Input, Select, Row, Col, message } from "antd";
import { SearchOutlined, EyeOutlined, UserOutlined } from "@ant-design/icons";
import { useNavigate } from "react-router-dom";
import dayjs from "dayjs";
import { Modal } from "antd";
import { MedicalResultForm } from "../../components/doctors/MedicalResultsForm";

interface Patient {
  id: number;
  fullname: string;
  idCard: string;
  address: string;
  gender: "MALE" | "FEMALE" | "OTHER";
  dateOfBirth: string;
  email: string | null;
  relation: string;
  bhyt: string;
  household: {
    id: number;
    househeadId: number;
    address: string;
    quantity: number;
    isActive: boolean;
  };
  memberStatus: string;
}


const genderMap: Record<string, string> = {
  MALE: "Nam",
  FEMALE: "Nữ",
  OTHER: "Khác",
};

const relationMap: Record<string, string> = {
  CHU_HO: "Chủ hộ",
  VO: "Vợ",
  CHONG: "Chồng",
  CON: "Con",
  BO: "Bố",
  ME: "Mẹ",
};

const DoctorPatientsList: React.FC = () => {
  const navigate = useNavigate();
  const [searchText, setSearchText] = useState("");
  const [genderFilter, setGenderFilter] = useState<string | undefined>(undefined);
  const [patients, setPatients] = useState<Patient[]>([]);
  const [loading, setLoading] = useState(true);
  const [editingPatient, setEditingPatient] = useState<Patient | null>(null);
  const [modalVisible, setModalVisible] = useState(false);


  const [resultsModalVisible, setResultsModalVisible] = useState(false);
  const [medicalResults, setMedicalResults] = useState<any[]>([]);

  const loadMedicalResults = async (memberId: number) => {
    try {
      const res = await fetch(`http://localhost:8080/familyhealth/api/v1/medical_results/members/${memberId}`, {
        headers: { 'Authorization': `Bearer ${localStorage.getItem('access_token')}` }
      });
      const data = await res.json();
      if (data.code === 200) setMedicalResults(data.data);
    } catch (err) {
      message.error("Không tải được kết quả y tế");
    }
  };
  useEffect(() => {
    loadPatients();
  }, []);

  const loadPatients = async () => {
    try {
      setLoading(true);
      const response = await fetch('http://localhost:8080/familyhealth/api/v1/doctors/doctor-patients', {
        headers: {
          'Authorization': `Bearer ${localStorage.getItem('access_token') || ''}`
        }
      });

      if (!response.ok) {
        throw new Error('Failed to load patients');
      }

      const data = await response.json();

      if (data.code === 200 && data.data) {
        setPatients(data.data);
      } else {
        throw new Error(data.message || 'Failed to load patients');
      }
    } catch (error) {
      console.error('Error loading patients:', error);
      message.error('Không thể tải danh sách bệnh nhân');
      setPatients([]);
    } finally {
      setLoading(false);
    }
  };

  const filteredData = patients.filter((p) => {
    const matchSearch = p.fullname.toLowerCase().includes(searchText.toLowerCase()) || p.idCard.includes(searchText);
    const matchGender = !genderFilter || p.gender === genderFilter;
    return matchSearch && matchGender;
  });

  const columns = [
    {
      title: "Họ tên",
      dataIndex: "fullname",
      key: "fullname",
      render: (text: string) => (
        <Space>
          <UserOutlined style={{ color: "#1890ff" }} />
          <strong>{text}</strong>
        </Space>
      ),
    },
    {
      title: "CCCD",
      dataIndex: "idCard",
      key: "idCard"
    },
    {
      title: "Ngày sinh",
      dataIndex: "dateOfBirth",
      key: "dateOfBirth",
      render: (date: string) => dayjs(date).format('DD/MM/YYYY')
    },
    {
      title: "Giới tính",
      dataIndex: "gender",
      key: "gender",
      render: (g: string) => <Tag color={g === "MALE" ? "blue" : "pink"}>{genderMap[g] || g}</Tag>,
    },
    {
      title: "Địa chỉ",
      dataIndex: "address",
      key: "address",
      ellipsis: true
    },
    {
      title: "BHYT",
      dataIndex: "bhyt",
      key: "bhyt",
      render: (v: string) => v || <Tag color="orange">Chưa có</Tag>
    },
    {
      title: "Quan hệ",
      dataIndex: "relation",
      key: "relation",
      render: (r: string) => <Tag color="green">{relationMap[r] || r}</Tag>
    },
    {
      title: "Trạng thái",
      dataIndex: "memberStatus",
      key: "memberStatus",
      render: (status: string) => (
        <Tag color={status === "ACTIVE" ? "green" : "red"}>
          {status === "ACTIVE" ? "Hoạt động" : "Không hoạt động"}
        </Tag>
      ),
    },
    {
      title: "Thao tác",
      key: "action",
      fixed: "right" as const,
      width: 150,
      render: (_: any, record: Patient) => (
        <Button
          type="primary"
          onClick={() => {
            setEditingPatient(record);
            setModalVisible(true);
          }}
        >
          Thêm kết quả
        </Button>
      ),
    },
    {
      title: "Xem kết quả y tế",
      key: "viewResults",
      fixed: "right" as const,
      width: 180,
      render: (_: any, record: Patient) => (
        <Button
          type="default"
          onClick={() => {
            loadMedicalResults(record.id);
            setResultsModalVisible(true);
          }}
        >
          Xem tất cả
        </Button>
      ),
    }

  ];

  return (
    <div>
      <Card
        title={
          <Space style={{ fontSize: 20, fontWeight: 600 }}>
            <UserOutlined style={{ color: "#1890ff" }} />
            Danh sách bệnh nhân
          </Space>
        }
        extra={<Tag color="blue">{filteredData.length} bệnh nhân</Tag>}
        style={{ marginBottom: 16 }}
      >
        <Row gutter={[16, 16]} style={{ marginBottom: 16 }}>
          <Col xs={24} sm={12} md={8}>
            <Input
              placeholder="Tìm theo tên hoặc CCCD"
              prefix={<SearchOutlined />}
              value={searchText}
              onChange={(e) => setSearchText(e.target.value)}
              allowClear
            />
          </Col>
          <Col xs={24} sm={12} md={8}>
            <Select
              placeholder="Lọc theo giới tính"
              style={{ width: "100%" }}
              value={genderFilter}
              onChange={setGenderFilter}
              allowClear
            >
              <Select.Option value="MALE">Nam</Select.Option>
              <Select.Option value="FEMALE">Nữ</Select.Option>
              <Select.Option value="OTHER">Khác</Select.Option>
            </Select>
          </Col>
        </Row>
      </Card>
      <Card>
        <Table
          rowKey="id"
          columns={columns}
          dataSource={filteredData}
          loading={loading}
          bordered
          pagination={{ pageSize: 10, showSizeChanger: true, showTotal: (total) => `Tổng ${total} bệnh nhân` }}
          scroll={{ x: 1200 }}
        />
        <Modal
          title={`Kết quả y tế - ${editingPatient?.fullname}`}
          visible={modalVisible}
          onCancel={() => setModalVisible(false)}
          footer={null}
        >
          {editingPatient && (
            <MedicalResultForm
              patientId={editingPatient.id}
              onSaved={() => {
                setModalVisible(false);
                loadPatients(); // refresh table if needed
              }}
            />
          )}
        </Modal>
        <Modal
          title={`Kết quả y tế - ${editingPatient?.fullname || ''}`}
          visible={resultsModalVisible}
          onCancel={() => setResultsModalVisible(false)}
          footer={null}
        >
          {medicalResults.length === 0 ? (
            <p>Không có kết quả y tế</p>
          ) : (
            <Table
              rowKey="id"
              dataSource={medicalResults}
              pagination={{ pageSize: 5 }}
              columns={[
                {
                  title: "Tên kết quả",
                  dataIndex: "name",
                  key: "name",
                },
                {
                  title: "Chẩn đoán",
                  dataIndex: "diagnose",
                  key: "diagnose",
                },
                {
                  title: "Ghi chú",
                  dataIndex: "note",
                  key: "note",
                },
                {
                  title: "Ngày tạo",
                  dataIndex: "createdAt",
                  key: "createdAt",
                  render: (date: string) => dayjs(date).format("DD/MM/YYYY"),
                },
              ]}
            />
          )}
        </Modal>

      </Card>
    </div>
  );
};

export default DoctorPatientsList;
