// Mock Data for the application

export const currentUser = {
  id: 'user1',
  name: 'Nguyen Van A',
  age: 35,
  gender: 'Male',
  email: 'nguyenvana@example.com',
  phone: '0912345678',
  address: '123 Le Loi, Quan 1, TP.HCM',
  avatar: 'https://api.dicebear.com/7.x/avataaars/svg?seed=Felix',
  bloodType: 'O+',
  allergies: ['Penicillin', 'Peanuts'],
  chronicConditions: ['Hypertension'],
  recentVitals: [
    { date: '2023-10-01', type: 'Blood Pressure', value: '120/80' },
    { date: '2023-10-01', type: 'Heart Rate', value: '72 bpm' },
    { date: '2023-10-15', type: 'Weight', value: '70 kg' },
  ],
  medicalHistory: [
     { id: 1, date: '2023-01-15', condition: 'Annual Checkup', doctor: 'Dr. Tran', hospital: 'Cho Ray Hospital', notes: 'All good.' },
     { id: 2, date: '2022-05-20', condition: 'Flu', doctor: 'Dr. Le', hospital: 'Family Clinic', notes: 'Prescribed antibiotics.' }
  ]
};

export const familyMembers = [
  {
    id: 'fam1',
    name: 'Tran Thi B',
    relation: 'Spouse',
    age: 32,
    gender: 'Female',
    avatar: 'https://api.dicebear.com/7.x/avataaars/svg?seed=Aneka',
    bloodType: 'A+',
    allergies: ['None'],
    chronicConditions: ['None'],
    medicalHistory: []
  },
  {
    id: 'fam2',
    name: 'Nguyen Van C',
    relation: 'Child',
    age: 8,
    gender: 'Male',
    avatar: 'https://api.dicebear.com/7.x/avataaars/svg?seed=Baby',
    bloodType: 'O+',
    allergies: ['Dust Mites'],
    chronicConditions: ['Asthma'],
    medicalHistory: [
         { id: 3, date: '2023-08-10', condition: 'Asthma Attack', doctor: 'Dr. Pham', hospital: 'Children Hospital', notes: 'Inhaler prescribed.' }
    ]
  }
];

export const doctors = [
  {
    id: 'doc1',
    name: 'Dr. Le Van Minh',
    specialty: 'Cardiology',
    hospital: 'Heart Institute',
    location: 'District 10, TP.HCM',
    rating: 4.8,
    avatar: 'https://api.dicebear.com/7.x/avataaars/svg?seed=DrMinh',
    available: true,
    tags: ['Hypertension', 'Heart Surgery']
  },
  {
    id: 'doc2',
    name: 'Dr. Pham Thi Mai',
    specialty: 'Pediatrics',
    hospital: 'Children Hospital 1',
    location: 'District 1, TP.HCM',
    rating: 4.9,
    avatar: 'https://api.dicebear.com/7.x/avataaars/svg?seed=DrMai',
    available: true,
    tags: ['Vaccination', 'Newborn Care']
  },
  {
    id: 'doc3',
    name: 'Dr. Tran Quoc Bao',
    specialty: 'Dermatology',
    hospital: 'City Skin Clinic',
    location: 'District 3, TP.HCM',
    rating: 4.7,
    avatar: 'https://api.dicebear.com/7.x/avataaars/svg?seed=DrBao',
    available: false,
    tags: ['Acne', 'Eczema']
  },
  {
    id: 'doc4',
    name: 'Dr. Nguyen Thi Lan',
    specialty: 'General Medicine',
    hospital: 'Family Medical Practice',
    location: 'District 2, TP.HCM',
    rating: 4.6,
    avatar: 'https://api.dicebear.com/7.x/avataaars/svg?seed=DrLan',
    available: true,
    tags: ['Flu', 'Checkups']
  }
];

export const feedbackData = [
  {
    id: 'fb1',
    date: '2023-10-20',
    doctorName: 'Dr. Le Van Minh',
    doctorAvatar: 'https://api.dicebear.com/7.x/avataaars/svg?seed=DrMinh',
    patientName: 'Nguyen Van A',
    status: 'Responded',
    summary: 'Review of Blood Pressure Logs',
    message: 'Your blood pressure readings look stable. Keep taking the medication as prescribed and avoid salty foods. Come back for a checkup in 3 months.',
    read: false
  },
  {
    id: 'fb2',
    date: '2023-10-18',
    doctorName: 'Dr. Pham Thi Mai',
    doctorAvatar: 'https://api.dicebear.com/7.x/avataaars/svg?seed=DrMai',
    patientName: 'Nguyen Van C',
    status: 'Responded',
    summary: 'Asthma Follow-up',
    message: 'If the coughing persists at night, please increase the usage of the inhaler to twice a day. Let me know if there are any side effects.',
    read: true
  }
];
