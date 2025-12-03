<template>
  <div class="mb-6 flex justify-between items-center">
    <div>
      <h3 class="text-xl font-semibold">Phản hồi của bác sĩ</h3>
      <p>Hộp thư đến để được tư vấn y tế và phản hồi từ các chuyên gia.</p>
    </div>
  </div>

    <div class="">
      <FeedbackCard
      :doctorTitle="'Tiến sĩ'"
      :doctorName="'Lê Văn Minh'"
      :patientName="'Nguyễn Văn A'"
      :isNew="true"
      :date="'2023-10-20'"
      :title="'Đánh giá nhật ký huyết áp'"
      :summary="'Chỉ số huyết áp của bạn có vẻ ổn định. Hãy tiếp tục dùng thuốc theo chỉ định và tránh ăn đồ mặn. Hãy quay lại khám...'"
    />
    
    <FeedbackCard
      :doctorTitle="'Bác sĩ'"
      :doctorName="'Trần Thị Hương'"
      :patientName="'Trần Văn B'"
      :isNew="false"
      :date="'2023-09-01'"
      :title="'Tổng kết kết quả xét nghiệm'"
      :summary="'Kết quả xét nghiệm máu cho thấy các chỉ số trong giới hạn bình thường. Lưu ý duy trì chế độ ăn giàu chất xơ và vitamin...'"
    />
    
    <FeedbackCard
      :doctorTitle="'Tiến sĩ'"
      :doctorName="'Lê Văn Minh'"
      :patientName="'Nguyễn Văn A'"
      :isNew="true"
      :date="'2023-10-20'"
      :title="'Đánh giá nhật ký huyết áp'"
      :summary="'Chỉ số huyết áp của bạn có vẻ ổn định. Hãy tiếp tục dùng thuốc theo chỉ định và tránh ăn đồ mặn. Hãy quay lại khám...'"
    />
    
    <FeedbackCard
      :doctorTitle="'Bác sĩ'"
      :doctorName="'Trần Thị Hương'"
      :patientName="'Trần Văn B'"
      :isNew="false"
      :date="'2023-09-01'"
      :title="'Tổng kết kết quả xét nghiệm'"
      :summary="'Kết quả xét nghiệm máu cho thấy các chỉ số trong giới hạn bình thường. Lưu ý duy trì chế độ ăn giàu chất xơ và vitamin...'"
    />
    </div>
</template>

<style scoped></style>
<script setup>
import FeedbackCard from '@/components/patient/FeedbackCard.vue'
</script>