# Bingo Card Generator (Executable edition!)

![Java](https://img.shields.io/badge/Java-17%2B-blue)  
![PDFBox](https://img.shields.io/badge/PDFBox-2.0.x-orange)
![JavaFX Scene Builder](https://img.shields.io/badge/JavaFX_Scene_Builder-17%2B-ff69b4)

I have a friend who likes to do a Bingo with her husband in which they try to find the most cars with unusual colors - so I decided to actually generate and print daily challenges for them while learning Java.

This is a Java application that creates printable Bingo cards from custom JSON data.

---

## ✨ Features
- ✅ Generate Bingo cards in PDF format in any size
- 🎨 Customizable text entries via JSON

---

## 🚀 Quick Start
### Prerequisites
- Java 24
- JavaFX 24
- Maven (for building)

```bash
# Clone and run  
git clone https://github.com/zackystardust/bingogenerator.git  
cd bingogenerator  
java -jar BingoGenerator_executable/BingoGenerator.jar  
```

---

## 📂 Project Structure
```
src/  
└── main/  
    ├── java/
    |   ├── module-info.java
    │   └── com/  
    │       └── zackystardust/  
    │           ├── Main.java           
    │           ├── model/              
    │           │   └── BingoCard.java  
    │           ├── builder/           
    │           │   └── PdfBuilder.java
    │           └── controller/
    │               └── BingoController.java
    └── resources/
        ├── com
        |   └── zackystardust
        |       └── views
        |           └── bingo-ui.fxml
        |
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

2. Create a new JSON data file in the `themes` folder inside the `BingoGenerator_executable` folder.

---

## 🔨 Building from Source
```bash
mvn clean package  
```
And then,
```bash
jpackage \
  --name BingoGenerator_executable \
  --input target/ \
  --main-jar bingo-generator-1.0.jar \
  --module-path "C:\path\to\javafx-sdk-24.0.1\lib" \
  --add-modules javafx.controls,javafx.fxml \
  --type app-image
```
The output will be a folder with the executable .jar file to open and custuomize your own Bingo sheet.

---

## 🎯 Roadmap
1. Turn the project into an API.

---

## 📜 License
MIT License  
