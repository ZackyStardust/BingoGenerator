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
git clone https://github.com/zackystardust/bingogenerator.git  
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
    │           ├── Main.java          
    │           ├── model/             
    │           │   └── BingoCard.java  
    │           └── builder/           
    │               └── PdfBuilder.java  
    └── resources/  
        └── data/  
            └── colors.json          
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
The output PDF will be in the root.

---

## ▶️ Download Ready-to-Run Version

For users who just want to run the program without compiling:

➡ **Executable Version**: Available in the [`executable` branch](https://github.com/ZackyStardust/BingoGenerator/tree/executable)  
📦 Includes:
- Pre-built JAR file
- JavaFX runtime libraries
- One-click launcher

```bash
git clone -b executable https://github.com/zackystardust/bingogenerator.git
```

---

## 🎯 Roadmap
1. Turn the project into an API.

---

## 📜 License
MIT License  
