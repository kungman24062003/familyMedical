import { Button, Input, Space } from "antd";
import { useState } from "react";

export const MedicalResultForm: React.FC<{ patientId: number; onSaved: () => void }> = ({ patientId, onSaved }) => {
    const [name, setName] = useState("");
    const [note, setNote] = useState("");
    const [diagnose, setDiagnose] = useState("");

    const handleSubmit = async () => {
        try {
            const response = await fetch('http://localhost:8080/familyhealth/api/v1/medical_results/create', {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                    Authorization: `Bearer ${localStorage.getItem("access_token")}`,
                },
                body: JSON.stringify({
                    memberId: patientId,
                    name,
                    note,
                    diagnose
                }),
            });

            if (!response.ok) throw new Error("Không thể lưu kết quả");

            onSaved(); // close modal and refresh list
        } catch (err) {
            console.error(err);
        }
    };

    return (
        <Space direction="vertical" style={{ width: "100%" }}>
            <Input
                value={name}
                onChange={(e) => setName(e.target.value)}
                placeholder="Tên kết quả (ví dụ: X-Ray, Blood Test)"
            />
            <Input.TextArea
                value={note}
                onChange={(e) => setNote(e.target.value)}
                rows={4}
                placeholder="Ghi chú của bác sĩ"
            />
            <Input
                value={diagnose}
                onChange={(e) => setDiagnose(e.target.value)}
                placeholder="Chẩn đoán"
            />
            <Button type="primary" onClick={handleSubmit}>
                Lưu
            </Button>
        </Space>
    );
};
export default MedicalResultForm;