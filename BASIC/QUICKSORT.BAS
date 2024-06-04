05 REM Quicksort 
10 DIM Array(10) : REM ARRAY WITH MAX 10 ELEMENTS 
20 REM ASK FOR 10 NUMBERS 
30 FOR I = 1 TO 10 
40 PRINT "TYPE A NUMBER: "; 
50 INPUT Array (I) 
60 NEXT I
70 low = 1
80 high = 10
90 temp = 0
100 GOTO 240

110 FOR J = 1 TO 10
120 PRINT Array (j)
130 NEXT J
190 END

210 high = s - 1
220 low = s + 1
230 IF low > high GOTO 100
240 IF low < high GOTO 300

300 piv = Array (high)
310 ind = low - 1
320 FOR K=low TO high
330 IF Array (K) < piv GOTO 500

340 temp = Array (ind + 1)
350 Array (ind + 1) = Array(high)
360 Array (high) = temp
370 s = ind + 1
380 GOTO 210


500 ind = ind + 1
510 temp = Array (ind)
520 Array (ind) = Array (K)
530 Array (K) = temp
540 NEXT K
550 GOTO 340