# 🧪 Data-Driven Testing on Citi Bank CD Calculator

This project showcases how I implemented **Data-Driven Testing** using **Selenium WebDriver**, **Java**, and **Excel** integration to automate and validate the Citi Bank **Certificate of Deposit (CD) Calculator**.

---

## 🔧 Tech Stack

- 🧠 Java  
- 🧪 Selenium WebDriver  
- 🧾 Apache POI (for Excel interaction)  
- 🖱️ JavaScriptExecutor Interface  
- ✅ TestNG (optional)  
- 💻 IntelliJ IDEA

---

## ✅ Key Features

- 📊 **Excel-Driven Automation**  
  Created a reusable **Excel utility** to read test input and write back validation results to the same sheet.

- 🎯 **Smart Element Interaction**  
  Used **JavaScriptExecutor** to handle click actions on elements blocked by overlays (where `.click()` was not effective).

- 🗂️ **Reusable Framework**  
  Designed the solution to be extensible for future calculators or web forms using the same Excel utility.

---

## 📁 Folder Structure


---

## 🚀 How to Run This Project

1. Clone the repository  
2. Open the project in **IntelliJ IDEA**  
3. Run `mvn install` to download dependencies  
4. Update Excel file path if needed in the test script  
5. Execute the test using IntelliJ or TestNG runner  
6. Validate results written back to Excel

---

## 🙋‍♀️ Author

**Sush** – QA Engineer | Building Automation Skills | Passionate about Data-Driven Testing  
📫 Let's connect on [LinkedIn](https://www.linkedin.com/in/sushmitha-murugesan/)

---

## 📌 Notes

- Make sure Excel files are **closed** before running tests to avoid permission errors.
- Add this to `.gitignore` to avoid Excel temp file issues:
