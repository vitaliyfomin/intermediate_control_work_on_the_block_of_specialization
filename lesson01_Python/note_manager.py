import csv
import os
from datetime import datetime
from typing import List, Dict

class NoteManager:
    def __init__(self, filename: str):
        self.filename = filename

    def create_note(self) -> None:
        notes = self.load_notes()
        new_note: Dict[str, str] = {}
        new_note["id"] = str(len(notes) + 1)
        new_note["title"] = input("Введите заголовок заметки: ")
        new_note["body"] = input("Введите текст заметки: ")
        new_note["date"] = datetime.now().strftime("%Y-%m-%d %H:%M:%S")
        notes.append(new_note)
        self.save_notes(notes)
        print("Заметка успешно создана!")

    def load_notes(self) -> List[Dict[str, str]]:
        notes: List[Dict[str, str]] = []
        if os.path.exists(self.filename):
            with open(self.filename, "r", newline="", encoding="utf-8") as file:
                reader = csv.DictReader(file)
                for row in reader:
                    notes.append(row)
        return notes

    def save_notes(self, notes: List[Dict[str, str]]) -> None:
        fieldnames = ["id", "title", "body", "date"]
        with open(self.filename, "w", newline="", encoding="utf-8") as file:
            writer = csv.DictWriter(file, fieldnames=fieldnames)
            writer.writeheader()
            writer.writerows(notes)

    def list_notes(self) -> None:
        notes = self.load_notes()
        for note in notes:
            print(f"{note['id']}. {note['title']} - {note['date']}")

    def edit_note(self, note_id: int) -> None:
        notes = self.load_notes()
        for note in notes:
            if note["id"] == str(note_id):
                note["title"] = input("Введите новый заголовок заметки: ")
                note["body"] = input("Введите новый текст заметки: ")
                note["date"] = datetime.now().strftime("%Y-%m-%d %H:%M:%S")
                self.save_notes(notes)
                print("Заметка успешно отредактирована!")
                return
        print("Заметка с таким id не найдена.")

    def delete_note(self, note_id: int) -> None:
        notes = self.load_notes()
        notes = [note for note in notes if note["id"] != str(note_id)]
        self.save_notes(notes)
        print("Заметка успешно удалена!")
