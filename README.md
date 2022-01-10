# Android-Study-Jams

Double Entry Book Keeping System of Accounting

<b> Problem Statement: </b>

Many small businesses are not able to hire a accountant for tracking their money. Software like Tally is too advance for this small businesses to use on daily basis. Apart from them, students and many individuels want to keep track of all their money. Almost every popular application out there is working on single entry book keeping system of accounting. 

<b> Proposed Solution : </b>

This project proposes a “Double Entry Book Keeping System” to manage your finance. There are basic three rules of accountint on which this application is working on.
- There has to be atleast 2 accounts to make a single transaction and whenever one account is credited other has to be debited.
- There are basically 2 types of account: 1) Real Account 2)Personal Account
- Real Account: Debit what comes in and credit what goes out. (i.e. Debit means + and Credit means - from account)
- Personal Account: Debit the receiver and credit the giver. (i.e. Debit means - and Credit means + from account)

To implement above concepts there are total 5 screens in application. From the first screen you can navigate to either Account List or Add Transaction. You can add new transaction in Add transaction. Account List contains all the accounts and you can add more accounts by clicking add button. By clicking on individuel accounts you can navigate to account details fragment which contains the ledger of all the transaction done in the selected account.

<img width="1500" alt="sampleimages" src="Images/ASJ SS .png">

<b> Functionality & Concepts used : </b>

- The App has a very simple and interactive interface which helps the users create,view accounts and transactions. Following are few android concepts used to achieve the functionalities in app :
- Constraint Layout : Most of the activities in the app uses a flexible constraint layout, which is easy to handle for different screen sizes.
- Simple & Easy Views Design : Use of familiar audience EditText with hints and interactive buttons made it easier for students to register or sign in without providing any detailed instructions pages. Apps also uses App Navigation to switch between different screens.
- RecyclerView : To present the list of different accounts ans transaction we used the efficient recyclerview.
- LiveData & Room Database : We used Room Database to save all the transactions and accounts. And LiveData to observe and update any changes made in database.


<b> Application Link & Future Scope : </b>

The app is currently in its basic form and developed focusing on its basic functionalities, You can access the app : [FIND .apk HERE](https://drive.google.com/drive/folders/1Sea8TMDLao_NXrxOxWh7DJtR90PqF1ot?usp=sharing).

In future, we plan to connect the application with internet so that database can be backed up. After final build this application can be used by different users to keep track of their finance. This app will allow users to manage their accounting on the tip of their fingers.
