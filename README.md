# MoniCov

## About
> This system is a COVID-19 quarantine isolation monitoring mobile application which aims to help facilitate and expedite the management of quarantine and isolation of patients affected by the disease.

## Setup
The following are the steps to run the mobile application project:

1. Clone the repository.
2. Open the repostory using Android Studio.
3. Configure Firebase authentication and real-time database.
4. Create an emulator in Android Studio using your preferred settings.
5. Lauch the mobile application.

## Motivation
> The motivation for creating this app stemmed from the fact that it makes it easier to monitor the patient's behavior and health. 
> The patient's acquaintances can view the patient's medical information in MoniCov to track their development. 

## Product Perspective 
> The COVID-19 Quarantine Isolation Monitoring Mobile Application is a mobile-based system.
> The database is hosted on Firebase, a cloud-hosted JSON-based database, which allows the mobile application to be accessed as long as there is an internet connection.

## Current Features
1. Patient System Module

> This module provides the functionality for patients to register and login into their accounts, check their status, and provides the following functions:

- Create an account
- Manage their account
- Log in to the system
- Navigate the patient’s menu
- Add health status
- Update health status
- View the profile of the assigned medical professional
- View prescriptions from medical professional in-charge
- Generate QR code for medical records

2. Medical Professional System Module

> This module provides the functionality for medical professionals to register and login into their accounts, edit the status of the patients, and provides the following functions:

- Create an account
- Manage their account
- Log in to the system
- Navigate the medical professional menu
- Add/update/delete the list of patients
- Add/update/delete the patient's status
- Give prescriptions to the patients

3. Acquaintance System Module

> This module provides the functionality for acquaintances to see the status of their patients and provides the following functions:

- Scan QR code
- Display patient’s status/records

## Future Work

* [ ] Generate One Time Code: This feature allows the medical professionals to create a one-time code that the patient may use to be added to the patient list.
* [ ] Upload QR-Code: This feature allows acquaintances to have the option to upload a QR-Code image, instead of only scanning.
* [ ] Medical Professional - Patient Instant Messaging: This feature allows both users to have a real-time text transmission with each other. Patients are able to communicate with their medical professionals, may it be questions, urgent health status, etc.
* [ ] Medical Professional - Patient Video Teleconferencing: Similar to instant messaging, this feature allows both users to communicate with each other with video and audio enabled. 
* [ ] Patient Medication Reminder Notification: This feature allows patients to set and be reminded of the medications they are supposed to take. 
