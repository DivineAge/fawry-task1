# Quantum Radar

---

## Project Structure

```
src/main/java/radar/
├── model/
│   ├── CarType.java          # Enum: PRIVATE | TRUCK | BUS
│   ├── Car.java   # Data captured by the physical radar
│   ├── Violation.java        # A single rule breach + its fee
│   └── Fine.java             # Aggregated violations for one plate number
├── rules/
│   ├── Rule.java             # Extension interface
│   ├── SpeedLimitRule.java   # Enforces max speed per car type
│   └── SeatbeltRule.java     # Enforces seatbelt requirement
├── service/
│   └── RadarService.java     # Orchestrator: processes car, stores fines
└── Main.java                 # Demo entry point
```

---

## Class & Method Reference

### `model/CarType.java`
```
enum CarType { PRIVATE, TRUCK, BUS }
```
Enumerates the three supported vehicle categories used by speed-limit rules to determine applicability.

---

### `model/Car.java`
Immutable snapshot of one radar capture event.

| Field / Method | Type | Description |
|---|---|---|
| `plateNumber` | `String` | Licence-plate identifier, e.g. `"ABC1234"`. |
| `date` | `LocalDateTime` | Timestamp of the radar capture. |
| `carType` | `CarType` | Category of the vehicle. |
| `speedKmh` | `int` | Measured speed in km/h. |
| `seatbeltOn` | `boolean` | `true` if the driver's seatbelt is fastened. |
| `getPlateNumber()` | `String` | Returns the plate number. |
| `getDate()` | `LocalDateTime` | Returns the capture timestamp. |
| `getCarType()` | `CarType` | Returns the vehicle category. |
| `getSpeedKmh()` | `int` | Returns the measured speed. |
| `isSeatbeltOn()` | `boolean` | Returns the seatbelt status. |

---

### `model/Violation.java`
Immutable value object representing a single rule breach.

| Field / Method | Type | Description |
|---|---|---|
| `ruleName` | `String` | Name of the `Rule` that produced this violation (used for statistics). |
| `description` | `String` | Human-readable breach description, e.g. `"speed of 94 exceeded max allowed 80"`. |
| `fee` | `int` | Monetary penalty in Egyptian Pounds. |
| `getRuleName()` | `String` | Returns the originating rule name. |
| `getDescription()` | `String` | Returns the violation description. |
| `getFeeEGP()` | `int` | Returns the fee. |

---

### `model/Fine.java`
Aggregates all `Violation`s for a single car into one issuable fine.

| Field / Method | Type | Description |
|---|---|---|
| `plateNumber` | `String` | Plate number of the offending vehicle. |
| `violations` | `List<Violation>` | Unmodifiable list of violations that triggered this fine. |
| `getPlateNumber()` | `String` | Returns the plate number. |
| `getViolations()` | `List<Violation>` | Returns the violation list. |
| `getTotalAmount()` | `int` | Sums all violation fees and returns the total in EGP. |
| `print()` | `void` | Prints the fine in the required format (see sample output below). |

---

### `rules/Rule.java`
The core extension interface. Implement this to add any new traffic rule.

| Method | Signature | Description |
|---|---|---|
| `evaluate` | `Optional<Violation> evaluate(Car car)` | Returns a `Violation` if the rule is broken, or `Optional.empty()` if compliant. |
| `getName` | `String getName()` | Short human-readable rule label used in statistics reports. |

---

### `rules/SpeedLimitRule.java`
Enforces a maximum speed for one specific `CarType`.

| Constructor Parameter | Type | Description |
|---|---|---|
| `carType` | `CarType` | The vehicle category this rule applies to. |
| `maxSpeedKmh` | `int` | Maximum allowed speed in km/h. |
| `fee` | `int` | Fine in EGP charged when the limit is exceeded. |



Default limits used in `Main`:
| Car Type | Max Speed | Fee |
|----------|-----------|-----|
| PRIVATE  | 80 km/h   | 300 EGP |
| TRUCK    | 60 km/h   | 500 EGP |
| BUS      | 70 km/h   | 400 EGP |

---

### `rules/SeatbeltRule.java`
Enforces the seatbelt requirement for **all** vehicle types.

| Constructor Parameter | Type | Description |
|---|---|---|
| `fee` | `int` | Fine in EGP charged when the seatbelt is not fastened. |


---

### `service/RadarService.java`
Central orchestrator of the system.

| Method | Signature | Description |
|---|---|---|
| `addRule` | `void addRule(Rule)` | Registers a new `Rule`. Call before processing car. |
| `process` | `Optional<Fine> process(Car car)` | Evaluates the car against every registered rule. Returns a `Fine` if ≥ 1 violation was found, otherwise `Optional.empty()`. |
| `getAllFines` | `List<Fine> getAllFines()` | Returns an unmodifiable list of all fines issued so far, each containing the plate number and total amount. |
| `getViolationStats` | `Map<String, Long> getViolationStats()` | Returns a map of `ruleName → violation count` across all issued fines. Rules with zero violations are omitted. |

---

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
