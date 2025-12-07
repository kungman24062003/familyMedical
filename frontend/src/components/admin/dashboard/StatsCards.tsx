import React from "react";
import { Card, Row, Col, Statistic } from "antd";
import {
  UserOutlined,
  TeamOutlined,
  MedicineBoxOutlined,
} from "@ant-design/icons";

interface StatsCardsProps {
  stats: {
    totalUsers: number;
    totalDoctors: number;
    totalPatients: number;
  };
}

const StatsCards: React.FC<StatsCardsProps> = ({ stats }) => {
  return (
    <Row gutter={[16, 16]}>
      <Col xs={24} sm={12} lg={8}>
        <Card>
          <Statistic
            title="Tổng người dùng"
            value={stats.totalUsers}
            prefix={<UserOutlined />}
            valueStyle={{ color: "#3f8600" }}
          />
        </Card>
      </Col>
      <Col xs={24} sm={12} lg={8}>
        <Card>
          <Statistic
            title="Bác sĩ"
            value={stats.totalDoctors}
            prefix={<MedicineBoxOutlined />}
            valueStyle={{ color: "#1890ff" }}
          />
        </Card>
      </Col>
      <Col xs={24} sm={12} lg={8}>
        <Card>
          <Statistic
            title="Bệnh nhân"
            value={stats.totalPatients}
            prefix={<TeamOutlined />}
            valueStyle={{ color: "#eb2f96" }}
          />
        </Card>
      </Col>
    </Row>
  );
};

export default StatsCards;
