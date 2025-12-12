# Green Property Exchange

##### A Sustainable Property Management Simulation

Green Property Exchange is a full-featured academic project designed to simulate an eco-friendly property rental platform. Developed in two major phases, it demonstrates clean object-oriented design, modular architecture, GUI development, and strong software engineering practices.

### Project Overview:
This project models a sustainable property-sharing system where owners manage eco-conscious property listings and guests book stays.

It contains:
- Well-architected classes with encapsulation and abstraction
- Transition from console-based OOP to full GUI application
- Inheritance & polymorphism for multiple property types
- Environmental pricing modifiers for realistic booking scenarios


---

#  Features

---

## **MCO1 — Console-Based Application (Core System)**

### Property Management

* Create properties with unique names
* 1–30 available dates
* Default base price of **PHP 1,500/night**
* Update property name and base price
* Remove properties without active reservations

---

###  View Property

* Full month calendar (**days 1–30**)
* Shows available and reserved dates
* Displays guest names or **“BOOKED”**
* High-level statistics:

    * Total available dates
    * Estimated earnings
* Detailed date and reservation breakdowns

---

###  Simulate Booking

**User Inputs**

* Guest name
* Check-in date
* Check-out date

**Validation Rules**

* No check-out on **day 1**
* No check-in on **day 30**
* No overlapping reservations

**Computed**

* Total reservation cost
* Nightly price breakdown

**Updates**

* Availability
* Reservation records

---

## **MCO2 — GUI Application & Advanced OOP**

###  Multiple Property Types (with Inheritance)

| Property Type     | Rate Modifier |
| ----------------- | ------------- |
| Eco-Apartment     | Base rate     |
| Sustainable House | +20%          |
| Green Resort      | +35%          |
| Eco-Glamping      | +50%          |

---

### Environmental Impact Pricing Modifier

* Each date can have a modifier between **80% and 120%**
* Modifier affects nightly booking cost
* Users can customize modifiers per date

---

### Enhanced GUI Calendar (Java Swing)

* Color-coded environmental indicators:

    * **Green:** 80–89%
    * **White:** 100%
    * **Yellow:** 101–120%
* Shows nightly price and availability
* Mouse-controlled interactions
* Includes environmental price legend

---

## Technologies & Concepts Used

* Java
* Java Swing
* UML Class Diagrams
* Encapsulation, Inheritance, Polymorphism
* SOLID Design Principles
* Strategy-ready architecture
* Javadoc documentation
* Test scripts

---

## Learning Outcomes Demonstrated

* Designing maintainable OOP systems
* Applying abstraction, modularity, and clean coding
* Migrating from console to GUI
* Handling complex domain logic (pricing, modifiers, availability)
* Creating intuitive GUI flows
* Writing tests and documentation
* Using version control effectively

---

##  Repository Contents

* `/controller` — Controller
* `/model` — Business logic and data
* `/view` — GUI resources (Java Swing)
* `Main.java` - GUI program
* `README.md` - Project overview and documentation

---

## Authors

**Wesley R. Hong**

**Joshua Carlos B. Samonte**

Project created for **CCPROG3**  
Academic Year **2025–2026**


