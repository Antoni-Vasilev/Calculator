package com.nexanet.calculator;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Calculate {

    private int loopError = 0;
    public List<String> resultList = new ArrayList<>();

    public String calc(String task) {
        String res = viewForBrackets(task, false);
        try {
            double result = Double.parseDouble(res);
            if (result == (int) result)
                return String.valueOf((int) result);
            else return String.valueOf(result);
        } catch (Exception e) {
            return res;
        }
    }

    private List<String> splitToParts(String task) {
        List<String> parts = new ArrayList<>();
        StringBuilder word = new StringBuilder();

        for (char letter : task.toCharArray()) {
            if (letter == '+' || letter == '/' || letter == '*' || letter == '(' || letter == ')' || letter == '%' || letter == '=' || letter == '<' || letter == '>') {
                if (!word.toString().isEmpty()) parts.add(word.toString());
                word = new StringBuilder();
                parts.add(String.valueOf(letter));
            } else if (letter == '-') {
                if (!word.toString().isEmpty()) parts.add(word.toString());
                word = new StringBuilder();
                word.append(letter);
            } else {
                if (word.toString().equals("sin") || word.toString().equals("cos") || word.toString().equals("tg") || word.toString().equals("cotg") || word.toString().equals("sqrt")) {
                    parts.add(word.toString());
                    word = new StringBuilder();
                }

                word.append(letter);
            }
        }
        parts.add(word.toString());
        return parts;
    }

    private String viewForBrackets(String task, boolean subView) {
        if (!subView) resultList.add(task);
        if (task.contains("(")) {
            if (task.contains(")")) {
                int firstBracket = task.indexOf('(') + 1;
                int lastBracket = task.indexOf('(') + 1;
                int openBracketCount = 1;
                for (int i = lastBracket; i < task.length(); i++) {
                    if (task.charAt(i) == '(') openBracketCount++;
                    else if (task.charAt(i) == ')') openBracketCount--;
                    if (openBracketCount == 0) {
                        lastBracket = i + 1;
                        break;
                    }
                }

                String newTask = task.substring(firstBracket, lastBracket - 1);
                String result = viewForBrackets(newTask, true);
                task = task.substring(0, firstBracket - 1) + result + task.substring(lastBracket, task.length());
            } else {
                throw new RuntimeException("Missing bracket");
            }
        } else {
            if (splitToParts(task).size() > 1) {
                resultList.add(String.format("Sub: %s = %s", task, calculate(task)));
                resultList.add("");
            } else resultList.add("");
            task = calculate(task);
            return task;
        }

        while (splitToParts(task).size() != 1) {
            task = viewForBrackets(task, false);
        }

        return task;
    }

    private String calculate(String task) {
        List<String> parts = splitToParts(task);

        try {
            for (int i = 0; i < parts.size(); i++) {
                String part = parts.get(i);

                switch (part) {
                    case "sin": {
                        double num1 = Double.parseDouble(parts.get(i + 1));
                        double result = Math.sin(num1);

                        parts.set(i, String.valueOf(result));
                        parts.remove(i + 1);
                        i = 0;
                        break;
                    }
                    case "cos": {
                        double num1 = Double.parseDouble(parts.get(i + 1));
                        double result = Math.cos(num1);

                        parts.set(i, String.valueOf(result));
                        parts.remove(i + 1);
                        i = 0;
                        break;
                    }
                    case "tg": {
                        double num1 = Double.parseDouble(parts.get(i + 1));
                        double result = Math.tan(num1);

                        parts.set(i, String.valueOf(result));
                        parts.remove(i + 1);
                        i = 0;
                        break;
                    }
                    case "cotg": {
                        double num1 = Double.parseDouble(parts.get(i + 1));
                        double result = 1 / Math.tan(num1);

                        parts.set(i, String.valueOf(result));
                        parts.remove(i + 1);
                        i = 0;
                        break;
                    }
                    case "sqrt": {
                        double num1 = Double.parseDouble(parts.get(i + 1));
                        double result = Math.sqrt(num1);

                        parts.set(i, String.valueOf(result));
                        parts.remove(i + 1);
                        i = 0;
                        break;
                    }
                    case "%": {
                        double num1 = Double.parseDouble(parts.get(i - 1));
                        double result = num1 / 100.0;

                        parts.set(i, String.valueOf(result));
                        parts.remove(i - 1);
                        i = 0;
                        break;
                    }
                }
            }

            for (int i = 0; i < parts.size(); i++) {
                String part = parts.get(i);

                if (part.equals("*")) {
                    double num1 = Double.parseDouble(parts.get(i - 1));
                    double num2 = Double.parseDouble(parts.get(i + 1));
                    double result = num1 * num2;

                    parts.set(i, String.valueOf(result));
                    parts.remove(i + 1);
                    parts.remove(i - 1);
                    i = 0;
                } else if (part.equals("/")) {
                    double num1 = Double.parseDouble(parts.get(i - 1));
                    double num2 = Double.parseDouble(parts.get(i + 1));
                    double result = num1 / num2;

                    parts.set(i, String.valueOf(result));
                    parts.remove(i + 1);
                    parts.remove(i - 1);
                    i = 0;
                }
            }

            for (int i = 0; i < parts.size(); i++) {
                String part = parts.get(i);

                if (part.equals("+")) {
                    double num1 = Double.parseDouble(parts.get(i - 1));
                    double num2 = Double.parseDouble(parts.get(i + 1));
                    double result = num1 + num2;

                    parts.set(i, String.valueOf(result));
                    parts.remove(i + 1);
                    parts.remove(i - 1);
                    i = 0;
                } else if (part.startsWith("-")) {
                    try {
                        double num1 = Double.parseDouble(parts.get(i - 1));
                        double num2 = Double.parseDouble(parts.get(i));
                        double result = num1 + num2;

                        parts.set(i, String.valueOf(result));
                        parts.remove(i - 1);
                        i = 0;
                    } catch (Exception ignored) {
                    }
                }
            }

            for (int i = 0; i < parts.size(); i++) {
                String part = parts.get(i);

                switch (part) {
                    case "=": {
                        double num1 = Double.parseDouble(parts.get(i - 1));
                        double num2 = Double.parseDouble(parts.get(i + 1));
                        boolean result = num1 == num2;

                        parts.set(i, result ? CalculatorActivity.context.getString(R.string._true) : CalculatorActivity.context.getString(R.string._false));
                        parts.remove(i + 1);
                        parts.remove(i - 1);
                        i = 0;
                        break;
                    }
                    case "<": {
                        double num1 = Double.parseDouble(parts.get(i - 1));
                        double num2 = Double.parseDouble(parts.get(i + 1));
                        boolean result = num1 < num2;

                        parts.set(i, String.valueOf(result));
                        parts.remove(i + 1);
                        parts.remove(i - 1);
                        i = 0;
                        break;
                    }
                    case ">": {
                        double num1 = Double.parseDouble(parts.get(i - 1));
                        double num2 = Double.parseDouble(parts.get(i + 1));
                        boolean result = num1 > num2;

                        parts.set(i, String.valueOf(result));
                        parts.remove(i + 1);
                        parts.remove(i - 1);
                        i = 0;
                        break;
                    }
                }
            }
        } catch (Exception e) {
            Log.i("ERROR", e.getMessage());
            if (loopError >= 10) throw e;
            loopError++;
            return parts.get(0);
        }

        return parts.get(0);
    }
}
