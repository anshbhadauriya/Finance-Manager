# 📱 Finance Manager / Expense Tracker App

## 👨‍💻 Developed by

**Ansh Bhadauriya**

---

## 📌 Overview

This is a Finance Manager (Expense Tracker) Android application built using **Kotlin and XML**.
The app enables users to track their income and expenses, categorize transactions, and view a clear summary of their financial status.

---

## ✨ Features

### 💰 Add Transactions

* Add income or expense
* Fields: amount, category, date, note
* Input validation for better user experience

### 📋 Home Screen

* Displays all transactions
* Shows latest entries using RecyclerView

### 📊 Summary Screen

* Total income
* Total expenses
* Remaining balance

### 🎨 UI/UX

* Clean fintech-style design
* Bottom navigation (3 tabs)
* Smooth and responsive user experience

---

## 🧱 Tech Stack

* **Language:** Kotlin
* **UI:** XML
* **Architecture:** MVVM (simplified)
* **Database:** Room (Local Storage)
* **Navigation:** Navigation Component

---

## 📂 Project Structure

```
com.example.financemanager

├── data/
│   ├── Transaction.kt
│   ├── TransactionDao.kt
│   └── AppDatabase.kt

├── repository/
│   └── TransactionRepository.kt

├── viewmodel/
│   └── TransactionViewModel.kt

├── ui/
│   ├── home/
│   │   ├── HomeFragment.kt
│   │   └── TransactionAdapter.kt
│   ├── add/
│   │   └── AddTransactionFragment.kt
│   └── summary/
│       └── SummaryFragment.kt

└── MainActivity.kt
```

```
res/
├── layout/
├── navigation/
├── menu/
├── drawable/
└── values/
```

---

## 📸 Screenshots

<img width="362" height="767" alt="image" src="https://github.com/user-attachments/assets/97287608-f2d9-46d2-99af-497b9ddba4dd" />  <img width="367" height="768" alt="image" src="https://github.com/user-attachments/assets/d8d86263-fe1d-4868-80c6-692539374265" />  <img width="365" height="767" alt="image" src="https://github.com/user-attachments/assets/3841a658-a338-4e7a-b8b8-28b4dfbefe7e" />
 




---



---

## 📦 APK

https://finance-manager-tuf.netlify.app/

---

## ⚙️ Setup Instructions

1. Clone the repository:

```
git clone https://github.com/anshbhadauriya/Finance-Manager
```

2. Open the project in Android Studio

3. Sync Gradle

4. Run the app on an emulator or physical device

---

## 🚀 Future Improvements

* Dark/Light mode toggle
* Charts & analytics
* Category icons
* Swipe to delete transactions

---

## 📄 Submission

* GitHub Repository ✅
* APK File ✅
* Demo Video ✅

---

## 💡 Note

This project was developed as part of an assignment to demonstrate Android development skills, UI/UX design, and clean code structure.
