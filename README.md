# 🏘️ Property Brokerage CLI App

A personal command-line application that simulates a property brokerage system. Users can register, log in, list properties, search, shortlist, and mark properties as sold. This app was built to practice object-oriented programming, CLI command parsing, and modular Java design.

## 🧩 Core Features

- 👤 **User Operations**
  - `RegisterCommand`: Register a new user (broker or buyer)
  - `LoginCommand`: Authenticate an existing user
  - `LogoutCommand`: Log out from the session

- 🏡 **Property Operations**
  - `ListPropertyCommand`: Brokers can list a property
  - `ViewListedCommand`: View all properties listed by the broker
  - `MarkSoldCommand`: Mark a property as sold

- 🔍 **Search and Discovery**
  - `SearchCommand`: Buyers can search for available properties by criteria
  - `ShortlistCommand`: Buyers can shortlist properties
  - `ViewShortlistedCommand`: View buyer's shortlisted properties

- 🆘 **Utility**
  - `HelpCommand`: Show available commands and their usage

## 🛠️ Tech Stack

- Java (Standard Edition)
- Command-line interface (no external libraries)
- OOP principles
- DS based storage (depending on implementation)
