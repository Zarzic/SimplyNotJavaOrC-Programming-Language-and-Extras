   10 REM THIS PROGRAM FINDS THE GRADE REPORT FOR A STUDENT
   20 INPUT "WHAT FILE"; F$
   30 OPEN F$ FOR INPUT AS #1
   31 PRINT "FILE: "; F$
   32 PRINT " "
   33 NN = 0
   34 INPUT #1;NN
   36 SEM_POINTS = 0
   37 INPUT #1; SEM_POINTS
   40 DIM AR$(100)
   50 FOR I = 3 TO NN
   70   INPUT #1;AR$(I)
   80 NEXT I
  500 DIM AR2$(100)
  501 N = 1
  502 IN = 0
  510 FOR J = 3 TO 100
  511   VALUE$ = MID$(AR$(J),21,20)
  521   FOR JJ = 1 TO 100
  522     IF AR2$(JJ) = VALUE$ THEN
    :       GO TO 526
    :     ELSE
    :       GO TO 525
    :     END IF
  525     IN = IN + 1
  526   NEXT JJ
  528   IF IN = 100 THEN
    :     GO TO 530
    :   ELSE
    :     GO TO 535
    :   END IF
  530   AR2$(N) = VALUE$
  531   IN = 0
  532   N = N + 1
  534   GO TO 538
  535   IN = 0
  538 NEXT J
  539 Y = 1
  540 DIM AR3$(100)
  550 FOR K = 1 TO (N - 1)
  560   FOR O = 3 TO NN
  570     IF AR2$(K) = MID$(AR$(O),21,20) THEN
    :       GO TO 580
    :     ELSE
    :       GO TO 630
    :     END IF
  580     AR3$(Y) = MID$(AR$(O),1,20)
  590     AR3$(Y + 1) = MID$(AR$(O),41,14)
  600     AR3$(Y + 2) = MID$(AR$(O),55,14)
  610     Y = Y + 3
  630   NEXT O
  640   AR3$(Y) = "CAT_CUT"
  650   Y = Y + 3
  651 NEXT K
  660 TRUE = 0
  661 VAL_FOR_AR2 = 1
  662 TOTALPOINTS = 0
  663 TOTALGOTPOINTS = 0
  664 CATPOSPOINTS = 0
  665 CATTOTALPOINTS = 0
  666 DIM AR4(N)
  667 VV = 1
  668 DIM AR5(N)
  669 DIM AR6(Y)
  679 FOR X = 1 TO (Y - 3) STEP 3
  680   IF AR3$(X) = "CAT_CUT" THEN
    :     GO TO 702
    :   ELSE
    :     GO TO 681
    :   END IF
  681   REM THIS IS AREA TO DO MATH FOR THE SPECFIC ASSIGNMENT
  682   POSPOINTS$ = AR3$(X + 1)
  683   POSPOINTS = 0
  684   FOR B = 1 TO LEN(POSPOINTS$)
  685     C$ = MID$(POSPOINTS$,B,1)
  686     IF C$ = " " THEN
    :       GO TO 688
    :     ELSE
    :       GO TO 687
    :     END IF
  687     POSPOINTS = POSPOINTS * 10 + ASC(C$) - ASC("0")
  688   NEXT B
  689   TOTALPOINTSEARNED$ = AR3$(X + 2)
  690   TOTALPOINTSEARNED = 0
  691   FOR U = 1 TO LEN(TOTALPOINTSEARNED$)
  692     H$ = MID$(TOTALPOINTSEARNED$,U,1)
  693     TOTALPOINTSEARNED = TOTALPOINTSEARNED * 10 + ASC(H$) - ASC("0")
  694   NEXT U
  695   AR6(X + 1) = POSPOINTS
  696   AR6(X + 2) = TOTALPOINTSEARNED
  698   CATPOSPOINTS = POSPOINTS + CATPOSPOINTS
  700   CATTOTALPOINTS = TOTALPOINTSEARNED + CATTOTALPOINTS
  701   GO TO 840
  702   AR4(VV) = CATPOSPOINTS
  703   AR5(VV) = CATTOTALPOINTS
  704   VV = VV + 1
  705   AR6(X) = 999
  820   TOTALPOINTS = CATPOSPOINTS + TOTALPOINTS
  830   TOTALGOTPOINTS = CATTOTALPOINTS + TOTALGOTPOINTS
  831   CATPOSPOINTS = 0
  832   CATTOTALPOINTS = 0
  840 NEXT X
 1901 XX = 1
 2000 FOR BB = 1 TO (N - 1)
 2010   PRINT AR2$(BB); "     ("; ((AR4(BB) * 100)/TOTALPOINTS); "%)"
 2020   PRINT "======================================="
 2030   FOR XX = XX TO Y STEP 3
 2040     IF AR6(XX) = 999 THEN
    :       GO TO 3002
    :     ELSE
    :       GO TO 2500
    :     END IF
 2500     PRINT AR3$(XX); AR6(XX + 2); " / "; AR6(XX + 1); "   "; ((AR6(XX + 2) * 100)/AR6(XX + 1)); "%"
 2510   NEXT XX
 3001   REM ADD CAT TOTAL FOR THIS AREA
 3002   PRINT "======================================="
 3003   PRINT "                    "; AR5(BB); " / "; AR4(BB); "  ("; ((AR5(BB) * 100)/AR4(BB)); "%)"
 3004   PRINT " "
 3005   XX = XX + 3
 3006   REM IS THIS AREA WORKING TO MOVE XX AHEAD?
 3010 NEXT BB
 4000 PRINT "CURRENT GRADE: "; ((TOTALGOTPOINTS * 100)/TOTALPOINTS); "%"
 4010 PRINT "MINIMUM FINAL GRADE: "; ((TOTALGOTPOINTS * 100)/SEM_POINTS); "%"
 4020 PRINT "MAXIMUM FINAL GRADE: "; ((SEM_POINTS - (TOTALPOINTS - TOTALGOTPOINTS)) * 100)/SEM_POINTS; "%"
 4030 CLOSE #1

