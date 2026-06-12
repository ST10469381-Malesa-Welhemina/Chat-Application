CHATAPP PART 3 – STORED MESSAGES

What's new in Part 3:
- New menu option 4: Stored Messages
- Submenu with 6 features:
  a. Show sender + recipient of all stored messages
  b. Show longest stored message
  c. Search message by ID
  d. Search all messages for a recipient
  e. Delete a message by hash (removes from JSON file)
  f. Full report (hash, recipient, message)
- Messages saved to chatdata.json (auto‑loaded on startup)
- Parallel arrays for sent, stored, disregarded, IDs, hashes, recipients, texts

How to run:
javac chatapp/*.java
java chatapp.ChatApp

Run Part 3 tests (JUnit 4):
java -cp .:junit-4.13.2.jar:hamcrest-core-1.3.jar org.junit.runner.JUnitCore chatapp.ChatAppTest

All 6 tests must pass.

GitHub: minimum 6 commits for Part 3, Actions auto‑runs tests.

All POE Part 3 requirements met.
