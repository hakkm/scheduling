package me.khabir.utils;

public class MathFunctions {
  public static int lcm(int a, int b) {
    return a * (b / gcd(a, b));
  }

  public static int gcd(int a, int b) {
    if (b == 0) {
      return a;
    }
    return gcd(b, a % b);
  }
}
