      ******************************************************************
      * Author:
      * Date:
      * Purpose:
      * Tectonics: cobc
      ******************************************************************
      * This is a great program.
       IDENTIFICATION DIVISION.
           PROGRAM-ID. AUTOADVISOR.
       ENVIRONMENT DIVISION.
       INPUT-OUTPUT SECTION.
       FILE-CONTROL.
           SELECT INPUT-FILE
           ASSIGN TO
           "C:\Users\remem\OneDrive\Documents\Classwork\sophomore"
           ORGANIZATION IS LINE SEQUENTIAL.
       DATA DIVISION.
       FILE SECTION.
       FD INPUT-FILE.
       01 wsstudentchar.
          88 Endoffile    VALUE HIGH-VALUES.
          02 wsstuchar    PIC X(50).

       WORKING-STORAGE SECTION.
       01 GPA PIC 999V999.
       01 GPATRUE PIC 9V99.
       01 credit PIC 9(5).
       01 creditfailed PIC 9(5).
       01 credittemp PIC 9(5).
       01 credittemp2 PIC 9(5).
       01 creditsuc PIC 9(5).
       01 wsline PIC X(50).
       01 firstblock PIC X(50).
       01 secondblock PIC 9(1).
       01 thirdblock PIC X(50).
       01 fourthblock PIC X(1).

       01 thirdblock2 PIC X(50).
       01 thirdblock3 PIC X(50).
       01 thirdblock4 PIC X(50).
       01 thirdblock5 PIC X(50).
       01 thirdblock6 PIC X(50).

       01 thirdblock7 PIC X(5).
       01 thirdblock8 PIC X(5).
       01 thirdblock9 PIC X(5).

       01 wstotaltable.
           05 ws-a PIC X(50) OCCURS 50 TIMES INDEXED BY wstotaltablectr.
       01 ctr1 PIC 9(5).
       01 wstotaltablerealctr PIC 9(4).

       01 wspretable.
           05 ws-b PIC X(50) OCCURS 50 TIMES.
       01 ctr2 PIC 9(5).
       01 hourattempt PIC 9(5).

       01 wsdoneclasses.
           05 ws-c PIC X(50) OCCURS 50 TIMES INDEXED BY
               wsdoneclassesctr.
       01 ctr3 PIC 9(5).

       01 wsprereq2.
           05 ws-d PIC X(50) OCCURS 5 TIMES.
       01 ctr4 PIC 9(5).

       01 wsprereq3.
           05 ws-e PIC X(50) OCCURS 3 TIMES.
       01 ctr5 PIC 9(5).
       01 ctr6 PIC 9(5).

       01 truthtable.
           05 ws-f PIC X(1) OCCURS 3 TIMES.
       01 ctr7 PIC 9(5).

       01 finalctr PIC 9(3).
       01 finalctr2 PIC 9(3).
       01 finalctr3 PIC 9(3).

       01 true1 PIC 9(1).
       01 true2 PIC X(1).
       01 true3 PIC 9(1).
       01 true4 PIC 9(1).

       01 inblock PIC X(1).

       01 countdone PIC 9(5).
       01 pretablecount PIC 9(5).

       01 ws-count PIC 9(5).

       PROCEDURE DIVISION.
       LINE_PROCESSING.
           SET wstotaltablectr TO 1.
      *     SET wspretablectr TO 1.
           SET wsdoneclassesctr TO 1.
           OPEN INPUT INPUT-FILE.
           MOVE 1 TO finalctr, wstotaltablerealctr, pretablecount, ctr1,
               ctr2, ctr3
           MOVE 0.0 TO GPA.
           PERFORM UNTIL Endoffile

               READ INPUT-FILE
                   AT END SET Endoffile TO TRUE
               END-READ

               MOVE wsstuchar TO wsline

               UNSTRING wsline DELIMITED BY "|"
                   INTO firstblock, secondblock, thirdblock,
                       fourthblock
               END-UNSTRING
               MOVE firstblock TO ws-a(ctr1)

               MOVE thirdblock TO ws-b(ctr2)

               ADD 1 TO wstotaltablerealctr, ctr1, ctr2
      *    This is the area handling credit amount.
      *    This area is done.
               ADD secondblock TO credit
               IF fourthblock NOT EQUAL TO SPACES THEN
                   MOVE secondblock TO credittemp
                   IF fourthblock EQUAL TO 'A' THEN
                       MULTIPLY credittemp BY 4 GIVING credittemp2
                       ADD credittemp2 TO GPA GIVING GPA
                       ADD credittemp TO creditsuc GIVING creditsuc
                       MOVE 0 TO credittemp
                       MOVE 0 TO credittemp2

                       MOVE firstblock to ws-c(ctr3)
                       ADD 1 TO ctr3
                   ELSE IF fourthblock EQUAL TO 'B' THEN
                       MULTIPLY credittemp BY 3 GIVING credittemp2
                       ADD credittemp2 TO GPA GIVING GPA
                       ADD credittemp TO creditsuc GIVING creditsuc
                       MOVE 0 TO credittemp
                       MOVE 0 TO credittemp2

                       MOVE firstblock to ws-c(ctr3)
                       ADD 1 TO ctr3
                   ELSE IF fourthblock EQUAL TO 'C' THEN
                       MULTIPLY credittemp BY 2 GIVING credittemp2
                       ADD credittemp2 TO GPA GIVING GPA
                       ADD credittemp TO creditsuc GIVING creditsuc
                       MOVE 0 TO credittemp
                       MOVE 0 TO credittemp2

                       MOVE firstblock to ws-c(ctr3)
                       ADD 1 TO ctr3
                   ELSE IF fourthblock EQUAL TO 'D' THEN
                       MULTIPLY credittemp BY 1 GIVING credittemp2
                       ADD credittemp2 TO GPA GIVING GPA
                       ADD credittemp TO creditsuc GIVING creditsuc
                       MOVE 0 TO credittemp
                       MOVE 0 TO credittemp2

                       MOVE firstblock to ws-c(ctr3)
                       ADD 1 TO ctr3
                   ELSE
                       MULTIPLY credittemp BY 0 GIVING credittemp2
                       ADD credittemp2 TO GPA GIVING GPA
                       ADD credittemp TO creditfailed GIVING
                           creditfailed
                       MOVE 0 TO credittemp
                       MOVE 0 TO credittemp2
                   END-IF
               END-IF
           END-PERFORM

           ADD 0.0 TO GPATRUE.
           IF (creditsuc EQUAL TO 0) AND (creditfailed EQUAL TO 0) THEN
               CONTINUE
           ELSE
               ADD creditsuc TO creditfailed GIVING hourattempt
               SUBTRACT creditsuc FROM creditfailed
               DIVIDE GPA BY hourattempt GIVING GPATRUE
           END-IF

           DISPLAY "FILE: csmajor".
           DISPLAY "GPA: " GPATRUE.
           DISPLAY "HOURS ATTEMPTED: " hourattempt.
           DISPLAY "HOURS COMPLETED: " creditsuc.
           SUBTRACT creditsuc FROM credit GIVING credit.
           DISPLAY "CREDIT REMAINING: " credit.

           IF credit EQUAL TO 0 THEN
               DISPLAY "NONE - CONGRATULATIONS!"
               STOP RUN
           ELSE
               CONTINUE
           END-IF

           DISPLAY " ".
           DISPLAY "POSSIBLE COURSES TO TAKE NEXT: ".
           SUBTRACT 1 FROM ctr3.
           PERFORM COURSE_DELETE 50 TIMES.

           SET wstotaltablectr TO 1.
           SET wsdoneclassesctr TO 1.

           MOVE 1 TO finalctr2.
           MOVE 1 TO finalctr3.
           MOVE 'F' TO true2.

           PERFORM UNTIL finalctr3 > 50
           MOVE 'F' TO true2
           IF ws-a(finalctr3) EQUAL TO 'X' THEN
               ADD 1 TO finalctr3
               ADD 1 TO finalctr2
               CONTINUE
           ELSE
               UNSTRING ws-b(finalctr2) DELIMITED BY SPACE
                   INTO thirdblock2, thirdblock3, thirdblock4,
                   thirdblock5, thirdblock6
               END-UNSTRING
               MOVE 1 TO ctr4
               MOVE thirdblock2 TO ws-d(1)
               MOVE thirdblock3 TO ws-d(2)
               MOVE thirdblock4 TO ws-d(3)
               MOVE thirdblock5 TO ws-d(4)
               MOVE thirdblock6 TO ws-d(5)

               IF ((thirdblock2 EQUAL TO SPACES) AND
                   (thirdblock3 EQUAL TO SPACES) AND
                   (thirdblock4 EQUAL TO SPACES) AND
                   (thirdblock5 EQUAL TO SPACES) AND
                   (thirdblock6 EQUAL TO SPACES)) THEN
                   MOVE 'T' TO true2
                   CONTINUE
               ELSE
                   PERFORM UNTIL ctr4 > 5
                       MOVE SPACE TO thirdblock7, thirdblock8,
                           thirdblock9
                       UNSTRING ws-d(ctr4) DELIMITED BY ','
                           INTO thirdblock7, thirdblock8, thirdblock9
                       END-UNSTRING
                       IF ((thirdblock7 EQUAL TO SPACES) AND
                           (thirdblock8 EQUAL TO SPACES) AND
                           (thirdblock9 EQUAL TO SPACES)) THEN
                           MOVE 'S' TO ws-f(1), ws-f(2), ws-f(3)
                       ELSE
                           MOVE 1 TO ctr5, ctr7
                           MOVE thirdblock7 TO ws-e(1)
                           MOVE thirdblock8 TO ws-e(2)
                           MOVE thirdblock9 TO ws-e(3)
                           MOVE 'F' TO ws-f(1), ws-f(2), ws-f(3)
                           PERFORM UNTIL ctr5 > 3
                               MOVE 1 TO ctr6
                               PERFORM UNTIL ctr6 > ctr3
                                   IF ws-c(ctr6) EQUAL TO ws-e(ctr5)
                                       THEN
                                       MOVE 'T' TO ws-f(ctr7)
                                   END-IF
                                   IF (ws-e(ctr5) EQUAL TO SPACES) THEN
                                       MOVE 'T' TO ws-f(ctr7)
                                   END-IF
                                   ADD 1 TO ctr6
                               END-PERFORM
                               ADD 1 TO ctr7
                               ADD 1 TO ctr5
                           END-PERFORM
                       END-IF
                       IF ((ws-f(1) EQUAL TO 'T') AND
                           (ws-f(2) EQUAL TO 'T')
                       AND (ws-f(3) EQUAL TO 'T')) THEN
                           MOVE 'T' TO true2
                       END-IF
                       ADD 1 TO ctr4
                   END-PERFORM
               END-IF

               IF true2 NOT EQUAL TO 'T' THEN
                   MOVE 'X' TO ws-a(finalctr3)
               END-IF

               ADD 1 TO finalctr3
               ADD 1 TO finalctr2
           END-PERFORM

           MOVE 1 TO finalctr.
           PERFORM COURSE_DISPLAY 50 TIMES.

           CLOSE INPUT-FILE.
           STOP RUN.

       COURSE_DELETE.
           SET wstotaltablectr TO 1.
           SEARCH ws-a
               AT END CONTINUE
               WHEN ws-a(wstotaltablectr) EQUAL TO ws-c(finalctr)
                   MOVE 'X' TO ws-a(wstotaltablectr)
           END-SEARCH.
           ADD 1 TO finalctr.

       COURSE_DISPLAY.
           IF ws-a(finalctr) EQUAL TO 'X' THEN
               CONTINUE
           ELSE
               DISPLAY ws-a(finalctr)
           END-IF.
           ADD 1 TO finalctr.
