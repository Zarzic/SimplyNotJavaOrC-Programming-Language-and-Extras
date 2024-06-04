       
       IDENTIFICATION DIVISION.
       PROGRAM-ID. GRADEREPORT.

      **************************************

       ENVIRONMENT DIVISION.

       INPUT-OUTPUT SECTION.
       FILE-CONTROL.
       SELECT REP ASSIGN TO INPUTFILE
       ORGANIZATION IS LINE SEQUENTIAL.

      **************************************

       DATA DIVISION.

       FILE SECTION.
       FD REP.
       01 REPDETAILS.
               02 ANAME             PIC X(20).
               02 CAT               PIC X(20).
               02 POSS              PIC 9(14).
               02 EARN              PIC 9(14).

       WORKING-STORAGE SECTION.
       01 EOFILE                    PIC 9(1).
       01 USERINPUTFILE             PIC X(25).
       01 WS-REP.
               02 WS-ANAME          PIC X(20). 
               02 WS-CAT            PIC X(20).
               02 WS-POS            PIC X(14).
               02 WS-EAR            PIC X(14).
       01 WS-TOTALEARN              PIC 9(14).
       01 WS-TOTALPOSS              PIC 9(14).
       01 WS-POSSTHUS               PIC 9(14).
       01 WS-PT                     PIC X(14) JUSTIFIED RIGHT.
       01 WS-POSS REDEFINES WS-PT   PIC 9(14).
       01 WS-ET                     PIC X(14) JUSTIFIED RIGHT.
       01 WS-EARN REDEFINES WS-ET   PIC 9(14).
       01 WS-CURGRADE               PIC 9(3)V9(2).
       01 WS-HUNDRED                PIC 9(3) VALUE 100.
       01 WS-MINFIN                 PIC 9(3)V9(2).
       01 WS-MAXFIN                 PIC 9(3)V9(2).
       01 WS-AGRADE                 PIC 9(3)V9(2).
       01 WS-WEIGHT                 PIC 9(3)V9(2).
       01 WS-EMPTYSPACE             PIC X(20).
       

      ************************************************************

       PROCEDURE DIVISION.
       GRADEREPORT.

           SET WS-TOTALEARN TO 0.
           DISPLAY "Enter file name of grade report: ".
           ACCEPT USERINPUTFILE
           MOVE USERINPUTFILE TO INPUTFILE
           OPEN INPUT REP
           READ REP INTO WS-TOTALPOSS
           PERFORM READ-THRU UNTIL EOFILE = 1
           CLOSE REP.
      
      * Calculate and display current grade
           DIVIDE WS-TOTALEARN BY WS-POSSTHUS GIVING WS-CURGRADE.
           MULTIPLY WS-CURGRADE BY WS-HUNDRED GIVING WS-CURGRADE.
           IF WS-CURGRADE >= 100 THEN DISPLAY
                   "Current Grade: " WS-CURGRADE(1:3)"%".
           IF WS-CURGRADE < 100 AND WS-CURGRADE > 9 THEN DISPLAY
                   "Current Grade: " WS-CURGRADE(2:2)"%".
           IF WS-CURGRADE < 10 THEN DISPLAY
                   "Current Grade: " WS-CURGRADE(3:1)"%".

      * Caluclate and display minimum final grade
           DIVIDE WS-TOTALEARN BY WS-TOTALPOSS GIVING WS-MINFIN.
           MULTIPLY WS-MINFIN BY WS-HUNDRED GIVING WS-MINFIN.
           IF WS-MINFIN IS >= 100 THEN DISPLAY 
                   "Minimum Final Grade: " WS-MINFIN(1:3)"%".
           IF WS-MINFIN < 100 AND WS-MINFIN > 9 THEN DISPLAY
                   "Minimum Final Grade: " WS-MINFIN(2:2)"%".
           IF WS-MINFIN < 10 THEN DISPLAY
                   "Minimum Final Grade: " WS-MINFIN(3:1)"%".

      * Calculate and display maximum final grade     
           SUBTRACT WS-POSSTHUS FROM WS-TOTALPOSS GIVING WS-MAXFIN.
           ADD WS-TOTALEARN TO WS-MAXFIN GIVING WS-MAXFIN.
           DIVIDE WS-MAXFIN BY WS-TOTALPOSS GIVING WS-MAXFIN.
           MULTIPLY WS-MAXFIN BY WS-HUNDRED GIVING WS-MAXFIN.
           IF WS-MAXFIN IS >= 100 THEN DISPLAY 
                   "Maximum Final Grade: " WS-MAXFIN(1:3)"%".
           IF WS-MAXFIN < 100 AND WS-MAXFIN > 9 THEN DISPLAY
                   "Maximum Final Grade: " WS-MAXFIN(2:2)"%".
           IF WS-MAXFIN < 10 THEN DISPLAY
                   "Maximum Final Grade: " WS-MAXFIN(3:1)"%".

           STOP RUN.

      *-------------------

       READ-THRU.

           READ REP INTO WS-REP
                   AT END MOVE 1 TO EOFILE
                   NOT AT END 
                        MOVE FUNCTION TRIM(WS-EAR, TRAILING) TO WS-ET
                        MOVE FUNCTION TRIM(WS-POS, TRAILING) TO WS-PT
                        ADD WS-EARN TO WS-TOTALEARN
                        ADD WS-POSS TO WS-POSSTHUS

      * Test the PRINT para that will do the final calculations and
      * formatting. Still needs to be tweaked with counters for
      * different displays. Just using "Group Project" as an example.
                        IF WS-CAT = "Group Project" THEN PERFORM PRINT
           END-READ.

      *-------------------

       PRINT.
      * Display the category name if it hasnt been displayed already.
      * Display, then use counter to keep track. If counter > 1, don't
      * display the category. Make sure the "===" only print once before
      * and after each category (will have to change current code).
        DISPLAY "=====================================================".
           DIVIDE WS-EARN BY WS-POSS GIVING WS-AGRADE.
           MULTIPLY WS-AGRADE BY WS-HUNDRED GIVING WS-AGRADE.
           IF WS-AGRADE >= 100 THEN DISPLAY
               WS-ANAME WS-EARN"/"WS-POS WS-AGRADE(1:3)"%".
           IF WS-AGRADE < 100 AND WS-AGRADE > 9 THEN DISPLAY 
               WS-ANAME WS-EARN"/" WS-POS WS-AGRADE(2:2)"%".
           IF WS-AGRADE < 10 THEN DISPLAY
                   WS-ANAME WS-EARN"/"WS-POS WS-AGRADE(3:1)"%".
        DISPLAY "=====================================================".
           DISPLAY WS-EMPTYSPACE.
