package lesson6;

import kotlin.NotImplementedError;
import lesson6.impl.GraphBuilder;

import java.util.*;

public class JavaGraphTasks {
    /**
     * Эйлеров цикл.
     * Средняя
     * <p>
     * Дан граф (получатель). Найти по нему любой Эйлеров цикл.
     * Если в графе нет Эйлеровых циклов, вернуть пустой список.
     * Соседние дуги в списке-результате должны быть инцидентны друг другу,
     * а первая дуга в списке инцидентна последней.
     * Длина списка, если он не пуст, должна быть равна количеству дуг в графе.
     * Веса дуг никак не учитываются.
     * <p>
     * Пример:
     * <p>
     * G -- H
     * |    |
     * A -- B -- C -- D
     * |    |    |    |
     * E    F -- I    |
     * |              |
     * J ------------ K
     * <p>
     * Вариант ответа: A, E, J, K, D, C, H, G, B, C, I, F, B, A
     * <p>
     * Справка: Эйлеров цикл -- это цикл, проходящий через все рёбра
     * связного графа ровно по одному разу
     */
    public static List<Graph.Edge> findEulerLoop(Graph graph) {
        // T = O(V^2 + E^2) - Проверка сущесвтования эйлерова цикла работает за О(V^2), т.к. посещается каждая вершина и
        // нахождение всех соседей вершины это О(V). Поиск эйлерова цикла требует прохождения по каждому ребру 1 раз
        // при этом на каждом шаге за О(Е) ищутся все ребра, инцидентные текущей вершине, что в сумме дает О(Е^2)
        // R = O(E) - два списка размером E

        // Проверка сущесвтования эйлерова цикла
        if (graph.getVertices().size() < 2) return new ArrayList<>();
        Set<Graph.Vertex> visited = new HashSet<>(graph.getVertices().size());
        boolean loopExist = dfs(graph, graph.getVertices().iterator().next(), visited);
        if (!loopExist || visited.size() != graph.getVertices().size()) return new ArrayList<>();

        Set<Graph.Edge> path = new HashSet<>(graph.getEdges().size());
        List<Graph.Edge> result = new ArrayList<>(graph.getEdges().size());
        euler(graph, graph.getVertices().iterator().next(), path, result);
        return result;
    }

    private static void euler(Graph graph, Graph.Vertex start, Set<Graph.Edge> visited, List<Graph.Edge> result) {
        Map<Graph.Vertex, Graph.Edge> connections = graph.getConnections(start);
        for (Graph.Vertex vertex : connections.keySet()) {
            if (!visited.contains(connections.get(vertex))) {
                visited.add(connections.get(vertex));
                euler(graph, vertex, visited, result);
                result.add(connections.get(vertex));
            }
        }
    }

    // Поиск в глубину для проверки существования эйлерова цикла
    private static boolean dfs(Graph graph, Graph.Vertex start, Set<Graph.Vertex> visited) {
        visited.add(start);
        Set<Graph.Vertex> neighbors = graph.getNeighbors(start);
        boolean result = neighbors.size() % 2 == 0;
        for (Graph.Vertex neighbor : neighbors) {
            if (!visited.contains(neighbor))
                if (!dfs(graph, neighbor, visited)) result = false;
        }
        return result;
    }

    /**
     * Минимальное остовное дерево.
     * Средняя
     * <p>
     * Дан связный граф (получатель). Найти по нему минимальное остовное дерево.
     * Если есть несколько минимальных остовных деревьев с одинаковым числом дуг,
     * вернуть любое из них. Веса дуг не учитывать.
     * <p>
     * Пример:
     * <p>
     * G -- H
     * |    |
     * A -- B -- C -- D
     * |    |    |    |
     * E    F -- I    |
     * |              |
     * J ------------ K
     * <p>
     * Ответ:
     * <p>
     * G    H
     * |    |
     * A -- B -- C -- D
     * |    |    |
     * E    F    I
     * |
     * J ------------ K
     */
    public static Graph minimumSpanningTree(Graph graph) {
        // T = O(E) - Для каждого ребра мы добавляем в граф вершины, которых еще там нет и соединяем их ребром.
        // R = O(V) - множество размером V.

        GraphBuilder graphBuilder = new GraphBuilder();
        // Запоминаем, какие вершины присоединены к остовному дереву
        Set<String> connected = new HashSet<>(graph.getVertices().size());

        for (Graph.Edge edge : graph.getEdges()) {
            if (!connected.contains(edge.getBegin().getName())) {
                connected.add(edge.getBegin().getName());
                graphBuilder.addVertex(edge.getBegin().getName());
                graphBuilder.addConnection(edge.getBegin(), edge.getEnd(), 1);
            }
            if (!connected.contains(edge.getEnd().getName())) {
                connected.add(edge.getEnd().getName());
                graphBuilder.addVertex(edge.getEnd().getName());
                graphBuilder.addConnection(edge.getBegin(), edge.getEnd(), 1);
            }
        }
        return graphBuilder.build();
    }

    /**
     * Максимальное независимое множество вершин в графе без циклов.
     * Сложная
     * <p>
     * Дан граф без циклов (получатель), например
     * <p>
     * G -- H -- J
     * |
     * A -- B -- D
     * |         |
     * C -- F    I
     * |
     * E
     * <p>
     * Найти в нём самое большое независимое множество вершин и вернуть его.
     * Никакая пара вершин в независимом множестве не должна быть связана ребром.
     * <p>
     * Если самых больших множеств несколько, приоритет имеет то из них,
     * в котором вершины расположены раньше во множестве this.vertices (начиная с первых).
     * <p>
     * В данном случае ответ (A, E, F, D, G, J)
     * <p>
     * Если на входе граф с циклами, бросить IllegalArgumentException
     */
    public static Set<Graph.Vertex> largestIndependentVertexSet(Graph graph) {
        throw new NotImplementedError();
    }

    /**
     * Наидлиннейший простой путь.
     * Сложная
     * <p>
     * Дан граф (получатель). Найти в нём простой путь, включающий максимальное количество рёбер.
     * Простым считается путь, вершины в котором не повторяются.
     * Если таких путей несколько, вернуть любой из них.
     * <p>
     * Пример:
     * <p>
     * G -- H
     * |    |
     * A -- B -- C -- D
     * |    |    |    |
     * E    F -- I    |
     * |              |
     * J ------------ K
     * <p>
     * Ответ: A, E, J, K, D, C, H, G, B, F, I
     */
    public static Path longestSimplePath(Graph graph) {
        throw new NotImplementedError();
    }


    /**
     * Балда
     * Сложная
     * <p>
     * Задача хоть и не использует граф напрямую, но решение базируется на тех же алгоритмах -
     * поэтому задача присутствует в этом разделе
     * <p>
     * В файле с именем inputName задана матрица из букв в следующем формате
     * (отдельные буквы в ряду разделены пробелами):
     * <p>
     * И Т Ы Н
     * К Р А Н
     * А К В А
     * <p>
     * В аргументе words содержится множество слов для поиска, например,
     * ТРАВА, КРАН, АКВА, НАРТЫ, РАК.
     * <p>
     * Попытаться найти каждое из слов в матрице букв, используя правила игры БАЛДА,
     * и вернуть множество найденных слов. В данном случае:
     * ТРАВА, КРАН, АКВА, НАРТЫ
     * <p>
     * И т Ы Н     И т ы Н
     * К р а Н     К р а н
     * А К в а     А К В А
     * <p>
     * Все слова и буквы -- русские или английские, прописные.
     * В файле буквы разделены пробелами, строки -- переносами строк.
     * Остальные символы ни в файле, ни в словах не допускаются.
     */
    static public Set<String> baldaSearcher(String inputName, Set<String> words) {
        throw new NotImplementedError();
    }
}
