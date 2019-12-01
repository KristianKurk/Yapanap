# Yapanap
by Kristian Kurktchiev

<b>Description:</b><br>
An app that displays info on the japanese alphabet.

<b>Stats:</b><br>

-3 Database tables<br>
-5 Activities (1 main, 2 displays, 2 settings)<br>
-3 Fragments<br>


<b>Elements of Complexity:</b><br>
Bottom navbar (not seen in class)<br>
Fetching API data within API data (two async responses)

<b>Checklist</b>
<ul>
<li>R1. My application aids both novice and advanced japanese learners.</li>
<li>R2. My application has a database. It consists of 3 tables.</li>
<li>R3. My application has all CRUD methods.</li>
<li>R4. My application has 5 activities. 3 of them are of decent complexity.</li>
<li>R5. My application passes data to an activity. It is one way, however.</li>
<li>R6. My application passes data to a fragment.</li>
<li>R7. My application has several recycler views.</li>
<li>R8. My application has 3 fragments.</li>
<li>R9. My application has a consistent and subjectively attractive UI.</li>
<li>R10. My application's UI is functional in both orientations, although there is room for improvement.</li>
<li>R11. My application validates input, by checking for null inputs and such.</li>
<li>R12. My application has an options menu.</li>
<li>R13. My application's options menu is decently useful.</li>
<li>R14. My application has a widget we haven't seen: The bottom navigation view.</li>
<li>R15. My application has navigation buttons all around.</li>
<ul>
<br>

<b>Documented Test Cases</b>
Id: DB-01
Description: Clicking on a hiragana returns information of said hiragana.
Prerequisites: Open the hiragana fragment.
Steps: 
1. Click on a hiragana
2. Verify that the appropriate information is displayed.
<br><br>
Id: DB-02
Description: Katakana user notes are saved to the database.
Prerequisites: Open the katakana fragment.
Steps: <br>
1. Click on a hiragana
2. Press on the plus next to the notes field.
3. Write in your note.
4. Press save.
5. Exit the app.
6. Open the app.
7. Go to the katakana that was clicked on previously in step 1.
8. Verify that the note is written there.
<br><br>
Id: DB-03
Description: Kanji are loaded on to the screen.
Prerequisites: None.
Steps: 
1. Click on the kanji button in the menu.
2. Wait for 5 seconds.
3. Verify that the list of kanji is displayed.






