package com.dou.test.design;

 public class Whip extends CondimentDecorator {

     Beverage beverage;

      public Whip(Beverage beverage){
          this.beverage = beverage;
      }

      @Override
      public String getDescription() {
           return beverage.getDescription() + " , Whip";
       }

       @Override
       public double cost() {
       return beverage.cost() + 0.3;
   }

     public static void main(String[] args) {
          Beverage beverage = new Espresso();
          System.out.println(beverage.getDescription() + " $" + beverage.cost());
          Beverage beverage2 = new DarkRoast();
          beverage2 = new Milk(beverage2);
          System.out.println(beverage2.getDescription() + " $" + beverage2.cost());
          beverage2 = new Mocha(beverage2);
          System.out.println(beverage2.getDescription() + " $" + beverage2.cost());
          beverage2 = new Whip(beverage2);
          System.out.println(beverage2.getDescription() + " $" + beverage2.cost());
      }
 }
