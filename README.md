# OOAD Parking Management System

## Description
This repository features a desktop-based automated Parking Management System engineered for the CCP6224-Object-Oriented Analysis and Design (OOAD) module at Multimedia University. The application resolves data consistency challenges across multiple user interfaces by coordinating operations through centralized design patterns, cleanly separating back-office administration from customer-facing operations.

## Tech Stack
* **Language:** Java
* **GUI Framework:** Java Swing / AWT
* **Design Paradigms:** Creational & Behavioral Design Patterns, Polymorphism, and Encapsulation
* **Environment:** BlueJ Development Environment

## Architectural Patterns & Core Design
* **Creational: Singleton Pattern:** To avoid desynchronized system states—such as duplicate revenue tracking, desynchronized vehicle data, or conflicting spot allocations—the central controller utilizes the Singleton pattern. This guarantees a unified, thread-safe single source of truth across all application panels. It is implemented using a private static instance variable, a private constructor, and a public static `getInstance()` method.
* **Behavioral: Strategy Pattern:** The fine-calculation engine leverages the Strategy pattern to decouple calculation rules from core system processes. It utilizes a unified `FineScheme` interface to dynamically execute runtime algorithm swaps between `FixedFineScheme`, `ProgressiveFineScheme`, and `HourlyFineScheme`.
* **Polymorphism & Abstraction:** Payment pipelines inherit a strongly polymorphic, abstract base layer (`Payment`), allowing flexible implementation routes for distinct sub-classes like `CashPayment` and `CardPayment` without modifying core structural validation code.

## Key Features & Technical Highlights
* **Dual-Interface Management Ecosystem:** Implements separate desktop operational terminals for Customers (handling vehicle entries, available spot matching, and checkout flows) and Administrators (tracking active live capacities, parsing accumulated revenue metrics, and updating dynamic fine strategies).
* **Deterministic Allocation Logic:** Features contextual business logic sub-routines that analyze incoming vehicle parameters to evaluate structural suitability boundaries across various spot profiles like Compact, Regular, Handicapped, and Reserved slots.
* **Algorithmic State Transitions:** Monitors ongoing transaction sequences (entry, fee calculation, and payment processing) while tracking outstanding user violations or special concessions, such as discounted pricing models for handicapped classifications.

## Project Structure
The project uses a flat directory layout (optimized for the BlueJ environment) containing the following core source files:

* `Main.java` — System initialization bootstrap.
* `ParkingLotSystem.java` — Centralized Singleton system controller pipeline.
* `ParkingLot.java` — Shared data structure tracking capacity state metrics and revenue accumulation.
* `ParkingSpot.java` & `Vehicle.java` — Structural entity definitions for parking nodes and assets.
* `Ticket.java` & `Receipt.java` — Transient model representations of ongoing and historical transactions.
* `FineScheme.java` & `FineRepository.java` — Strategy design interfaces and local data collections for penalty tracking.
* `FixedFineScheme.java`, `ProgressiveFineScheme.java`, `HourlyFineScheme.java` — Concrete algorithmic strategies for fine evaluation.
* `AdminDashboard.java` — Tabbed telemetry manager panel for administrators.
* `CustomerGUI.java` — Interface panel for customer ticketing and payment entries.
* `ParkingLotPanel.java`, `ReportsPanel.java`, `FineSchemePanel.java` — Modular graphic interfaces embedded within the system workflows.
* `Payment.java` — Abstract payment handling engine.

*(Note: Environment-specific metadata files like `.class`, `.ctxt`, and `package.bluej` manage local compiler states and are omitted from production source listings).*

## Getting Started

### Prerequisites
* Java Development Kit (JDK 8 or higher)

### Compilation and Execution
1. Clone the repository framework locally:
   ```bash
   git clone [https://github.com/mehdi-raza-011/Parking-Management-System.git](https://github.com/mehdi-raza-011/Parking-Management-System.git)

   Bash
javac *.java
java Main
