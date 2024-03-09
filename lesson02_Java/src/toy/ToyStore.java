package toy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// Класс, представляющий магазин игрушек
class ToyStore {
    private final List<Toy> toys; // Список игрушек в магазине

    public ToyStore() {
        toys = new ArrayList<>();
    }

    // Метод для добавления новой игрушки в магазин
    public void addToy(Toy toy) {
        toys.add(toy);
    }

    // Метод для проведения розыгрыша
    public Toy drawToy(double maxWeight) {
        List<Toy> eligibleToys = new ArrayList<>();
        for (Toy toy : toys) {
            if (toy.weight() <= maxWeight) {
                eligibleToys.add(toy);
            }
        }

        if (eligibleToys.isEmpty()) {
            System.out.println("Нет игрушек, подходящих для данного веса.");
            return null;
        }

        Random random = new Random();
        int randomIndex = random.nextInt(eligibleToys.size());
        return eligibleToys.get(randomIndex);
    }

    public static void main(String[] args) {
        ToyStore toyStore = new ToyStore();

        // Добавляем игрушки в магазин
        toyStore.addToy(new Toy("Мяч", 0.2));
        toyStore.addToy(new Toy("Кукла", 0.5));
        toyStore.addToy(new Toy("Машинка", 0.3));
        toyStore.addToy(new Toy("Конструктор", 0.8));

        // Вес для проведения розыгрыша
        double maxWeight = 0.5;

        // Проводим розыгрыш
        Toy winner = toyStore.drawToy(maxWeight);

        if (winner != null) {
            System.out.println("Выиграла игрушка: " + winner.name());
        } else {
            System.out.println("К сожалению, розыгрыш не состоялся.");
        }
    }
}
