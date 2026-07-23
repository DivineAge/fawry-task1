# Quantum Radar

## Sample Output

```
----------------------------
        QUANTUM RADAR - FINES
----------------------------

Traffic for car ABC1234
Total amount: 400 EGP
Violations:
- speed of 94 exceeded max allowed 80 : 300 EGP
- Seatbelt not fastned : 100 EGP

Traffic for car TRK9999
Total amount: 500 EGP
Violations:
- speed of 75 exceeded max allowed 60 : 500 EGP

Traffic for car BUS0001
Total amount: 100 EGP
Violations:
- Seatbelt not fastned : 100 EGP

----------------------------
         ALL FINES SUMMARY
----------------------------
ABC1234       400 EGP
TRK9999       500 EGP
BUS0001       100 EGP

----------------------------
       VIOLATED RULES STATS
----------------------------
Speed Limit - Private           1 time(s)
Seatbelt                        2 time(s)
Speed Limit - Truck             1 time(s)

```

---

## How to Run

### Compile
```bash
# From project root
javac -d out $(find src -name "*.java")
```

### Run
```bash
java -cp out radar.Main
```

---
