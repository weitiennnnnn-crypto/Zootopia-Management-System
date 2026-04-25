<div align="center">
  <h1><strong>🐾 ZooToPia Management System (Java Swing)</strong></h1>
  <p>A custom management solution built for DCS FTMK coursework.</p>
</div>

---

### **Project Overview**
This is a Zoo Management System we built for our **DCS FTMK** coursework (Object Oriented Programming). We wanted to move away from basic console apps and build a full GUI that actually handles real-world zoo tasks like animal tracking and ticket sales.

### **Design Inspiration: Minecraft UI**
One of the unique parts of this project is the interface. I am a huge fan of the **Minecraft aesthetic**, so we designed the UI to reflect that. 
* We used **blocky layouts** and **pixel-art inspired icons**.
* Instead of the standard boring Java look, we used `TitledBorder` and custom colors to give it that retro, "indie game" vibe.

### **The Tech Behind It**
We used **Java Swing** for the entire interface. The most challenging part was probably the layout management—getting everything to stay in place using `GridBagLayout` was a bit of a nightmare, but it allowed us to build custom dialogs that look organized.

* **Logic:** Heavily focused on **OOP (Inheritance & Encapsulation)**.
* **UI Components:** Used a mix of `JTree` for navigation and `JDialog` for pop-up forms (Add, Remove, and Import).
* **Listeners:** We implemented `FocusListener` on the text fields so the UI feels more responsive when you're typing.

### **Features That Actually Work**

* **Dynamic Ticket Calculator:** This was fun to code. It calculates prices for Children, Adults, and Seniors, and even switches rates based on whether the visitor is **Malaysian or Non-Malaysian**.
* **The Tree System:** Instead of a flat list, we used a `JTree` to organize animals. When you import a file, the tree updates itself automatically.
* **Data Handling:** You can add, update, and **"release" (delete)** animals. All animal details like health and diet are stored and manageable through the UI.

### **How It Looks**
*****Please view the presentation slide uploaded*****

### **Team**
This is a Zootopia Management System and we, 4 dedictaed members have developed this system:
* **Ashlee Sia           | D032410085 | S3G2**
* **Asyraf Raziq         | D032410118 | S3G2**
* **Jia Poh              | D032410304 | S3G2**
* **Kai Dit              | D032410113 | S3G1**

*****DO NOT OPEN INSIDE THE SRC FILE, AS IT MAY NOT WORK UNEXPECTEDLY,*****
*****EXTRACT THE ZIP FILE AND RUN ON $HOME/Zootopia-Management-System/ DIRECTORY*****


