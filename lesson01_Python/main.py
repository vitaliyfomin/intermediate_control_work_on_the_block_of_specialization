from note_manager import NoteManager

def main():
    manager = NoteManager("notes.csv")
    while True:
        print("\nМеню:")
        print("1. Создать заметку")
        print("2. Показать список всех заметок")
        print("3. Редактировать заметку")
        print("4. Удалить заметку")
        print("5. Выйти")

        choice = input("Выберите действие: ")

        if choice == "1":
            manager.create_note()
        elif choice == "2":
            manager.list_notes()
        elif choice == "3":
            try:
                note_id = int(input("Введите id заметки для редактирования: "))
                manager.edit_note(note_id)
            except ValueError:
                print("Некорректный формат id. Введите целое число.")
        elif choice == "4":
            try:
                note_id = int(input("Введите id заметки для удаления: "))
                manager.delete_note(note_id)
            except ValueError:
                print("Некорректный формат id. Введите целое число.")
        elif choice == "5":
            break
        else:
            print("Некорректный выбор. Попробуйте еще раз.")

if __name__ == "__main__":
    main()
