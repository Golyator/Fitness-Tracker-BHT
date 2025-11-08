# Klassendiagramm-Notizen

## Entscheidung: Keine Verwendung von Interfaces

**Begründung:**  
In diesem System gibt es keine unterschiedlichen Klassen, die dieselben Methoden mit unterschiedlicher Implementierung bereitstellen müssten.  
Ein Interface wäre daher nur zusätzlicher formaler Overhead ohne echten Mehrwert.  
Die bestehenden Klassen haben klar getrennte Verantwortlichkeiten, und es besteht keine Notwendigkeit für eine polymorphe Nutzung über eine gemeinsame Schnittstelle.  
Daher wurde bewusst auf die Einführung eines Interfaces verzichtet.
