# ConsoleHoroscope
A Java-based Console Application that asks for some prior details which then uses that data to retrieve an appropriate horoscope message retreived from an online API. The inspiration for this project is that I wanted to initially create a mobile application with this concept, but I haven't had much time to actually design the UI. I also wanted to explorer the uses of online APIs and JSON objects.
<br />
<br />
**How to start the application**
```
java ConsoleHoroscope
```
The user will be asked for two pieces of information: their date of birth (dd/mm) and their choice of horoscope (either from daily, weekly, monthly and annual types).
<br />
<br />
**Example Input and Ouput**
```
Date of birth: 29/01, Horoscope Type: Yearly
```
The above inputs will gather the following answer (as of 2016):
```
Starsign: Aquarius (Janurary 29)
Horoscope: 
As per the planetary alignments, Ganesha feels that this year you need to do some deep introspection. The process of contemplations will only you to restore stability in the long run. While exploring opportunities and seeking a concrete direction for growth will keep you occupied, you will be able to review your course from around the second week of January. Time for action commences from January 25 onwards. Monetary aspirations will lead to acquiring more wealth. However the presence of malefic Rahu and that of Ketu in the Second House are going to impact the returns in a negative manner. In addition, Jupiter transiting through the Eighth House sounds a caution to not incur any financial debt because repayment of debt will be a concern. You could apply a loan for asset acquisition or loan for automobile and household requirement, after Jupiter enters Libra. Patiently pursuing ongoing goals and tasks without entertaining major changes related to occupation, whether business or a job, will enable you achieve positive growth. In job, shouldering more responsibility and meticulous approach in work will enable good results. Do not blow your own trumpet, but let your work speak for itself, and recognition will come sooner or later."
```
**Still to do**
- [X] ~~Read what is given in the HTTP response.~~
- [ ] Parse the string to JSON and extract relevant information.
- [ ] Find an effecient method to allow users to include their year of birth. (As of now, default year is 1970).
