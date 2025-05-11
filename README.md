# Bingo Card Generator

![Java](https://img.shields.io/badge/Java-17%2B-blue)  
![PDFBox](https://img.shields.io/badge/PDFBox-2.0.x-orange)

I have a friend who likes to do a Bingo with her husband in which they try to find the most cars with unusual colors - so I decided to actually generate and print daily challenges for them while learning Java.

This is a Java application that creates printable Bingo cards from custom JSON data.

---

## ✨ Features
- ✅ Generate Bingo cards in PDF format in any size
- 🎨 Customizable text entries via JSON
- 📦 Self-contained (no external dependencies beyond PDFBox)

---

## 🚀 Quick Start
### Prerequisites
- Java 17 or later
- Maven (for building)

```bash
# Clone and run  
git clone https://github.com/yourusername/bingo-generator.git  
cd bingo-generator  
mvn package  
java -jar target/bingo-generator.jar  
```

---

## 📂 Project Structure
```
src/  
└── main/  
    ├── java/  
    │   └── com/  
    │       └── zackystardust/  
    │           ├── Main.java          # Application entry  
    │           ├── model/             # Data classes  
    │           │   └── BingoCard.java  
    │           └── builder/           # PDF logic  
    │               └── PdfBuilder.java  
    └── resources/  
        └── data/  
            └── colors.json           # Sample data   
```
---

## 🛠️ Customization
1. Edit the JSON data file:
```json
{  
  "entries": ["RED", "BLUE", "GREEN", "YELLOW"]  
}  
```

2. Modify grid size in `Main.java`:
```java
int gridSize = 3; // Change to 5 for 5x5 cards  
```

3. Create a new JSON data file in the `data` folder, then edit in the `BingoSelect.java` file:
``` java
 try (InputStream is = getClass().getClassLoader().getResourceAsStream("data/yourFile.json"))
```

---

## 🔨 Building from Source
```bash
mvn clean package  
```
And then,
```bash
jpackage \
  --name BingoGenerator \
  --input target/ \
  --main-jar bingo-generator-1.0.jar \
  --module-path "C:\path\to\javafx-sdk-24.0.1\lib" \
  --add-modules javafx.controls,javafx.fxml \
  --type app-image
```
The output will be a folder with the executable .jar file to open and custuomize your own Bingo sheet.

---

## 🎯 Roadmap
2. Turn the project into an API.

---

## 📜 License
MIT License  
