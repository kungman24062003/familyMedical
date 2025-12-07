import React, { useState } from "react";
import {
  Form,
  Input,
  Button,
  Space,
  App as AntApp,
  Typography,
} from "antd";
import { UserOutlined, LockOutlined } from "@ant-design/icons";
import logo from "../../assets/logo.png"
import { useNavigate } from "react-router-dom";
import { useAppDispatch } from "../../redux/hooks";
import { setUserLoginInfo } from "../../redux/slice/accountSlice";
import { authService } from "../../services/authService";

const { Title, Text } = Typography;

const Login: React.FC = () => {
  const navigate = useNavigate();
  const dispatch = useAppDispatch();
  const [loading, setLoading] = useState(false);
  const { message } = AntApp.useApp();

  const onFinish = async (values: any) => {
    setLoading(true);
    try {
      let res;
      try {
        res = await authService.login({
          phone: values.username,
          password: values.password,
        });
      } catch (err: any) {
        const errorMsg = err?.message || "Đăng nhập thất bại";
        message.error({ content: errorMsg, duration: 5 });
        setLoading(false);
        return;
      }

      if (res && res.code === 200 && res.data) {
        authService.setTokens(
          res.data.accessToken,
          res.data.refreshToken,
          res.data.userId
        );

        try {
          const profileRes = await authService.getProfile();
          if (profileRes?.data) {
            const userInfo = {
              id: profileRes.data.id,
              email: profileRes.data.email || "",
              name: profileRes.data.fullName,
              phone: profileRes.data.phone,
              role: { id: "", name: profileRes.data.roleName },
            };
            authService.setUserInfo(userInfo);
            dispatch(setUserLoginInfo(userInfo));
          }
        } catch (e) {}

        message.success("Đăng nhập thành công");
        navigate("/");
      } else {
        message.error(res?.message || "Đăng nhập thất bại");
      }
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
        {/* CARD GLASS */}
        <div
          style={{
            width: "100%",
            maxWidth: 420,
            padding: "50px 40px",
            background: "rgba(255, 255, 255, 0.15)",
            borderRadius: 20,
            boxShadow: "0 20px 60px rgba(0,0,0,0.25)",
            backdropFilter: "blur(15px)",
            border: "1px solid rgba(255,255,255,0.2)",
          }}
        >
          {/* Logo */}
          <div style={{ textAlign: "center", marginBottom: 30 }}>
            <img
              src={logo}
              alt="Logo"
              style={{
                width: 100,
                height: "auto",
                transition: "0.2s",
              }}
            />
            <Title level={2} style={{ color: "white", marginBottom: 0 }}>
              FamilyMedicalRecords
            </Title>
            <Text style={{ color: "rgba(255,255,255,0.8)" }}>
              Hệ thống quản lý hồ sơ y tế
            </Text>
          </div>

          {/* Login Form */}
          <Form
            name="login"
            onFinish={onFinish}
            layout="vertical"
            requiredMark={false}
            size="large"
          >
            <Form.Item
              label={<span style={{ color: "#fff" }}>Số điện thoại</span>}
              name="username"
              rules={[
                { required: true, message: "Vui lòng nhập số điện thoại" },
                { pattern: /^[0-9]{10,11}$/, message: "Số điện thoại không hợp lệ" },
              ]}
            >
              <Input
                prefix={<UserOutlined style={{ color: "#e0e7ff" }} />}
                placeholder="0123xxxxxx"
                style={{
                  height: 50,
                  borderRadius: 10,
                  background: "rgba(255,255,255,0.25)",
                  color: "white",
                }}
              />
            </Form.Item>

            <Form.Item
              label={<span style={{ color: "#fff" }}>Mật khẩu</span>}
              name="password"
              rules={[{ required: true, message: "Vui lòng nhập mật khẩu" }]}
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

            <Form.Item>
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
                  background: "linear-gradient(135deg, #3b82f6, #06b6d4)",
                  border: "none",
                }}
              >
                Đăng nhập
              </Button>
            </Form.Item>
          </Form>

          {/* Register */}
          <div style={{ textAlign: "center", marginTop: 20 }}>
            <Text style={{ color: "#e0e7ff" }}>
              Chưa có tài khoản?{" "}
              <a
                href="/register"
                style={{
                  color: "#fff",
                  fontWeight: "bold",
                  textDecoration: "underline",
                }}
              >
                Đăng ký ngay
              </a>
            </Text>
          </div>
        </div>
      </div>
    </AntApp>
  );
};

export default Login;
