/* Created: Bob Roos, 3 October 2013
Edited: Anastasia Kurdia, 11 January 2016
This program models the work of an octopus in the kitchen 
and tests the work of Octopus and Utensil class functions
*/
 

public class Lab0
{
    public static void main(String[] args)
    {
        // Variable dictionary:
        Octopus ocky;           // an octopus
        Utensil spat;           // a kitchen utensil
        Octopus rocky;          // new octopus
        Utensil sproon;         // new kitchen utensil

        spat = new Utensil("spatula"); // create a spatula
        spat.setColor("green");        // set spatula properties--color...
        spat.setCost(10.59);           // ... and price

        sproon = new Utensil("spork spoon");
        sproon.setColor("purple");      //sets sproon color
        sproon.setCost(5.49);       //the price of a sproon

        ocky = new Octopus("Ocky", 30);    // create and name the octopus
        //  ocky.setAge(10);               // set the octopus's age...
        ocky.setWeight(100);           // ... weight,...
        ocky.setUtensil(spat);         // ... and favorite utensil.

        rocky = new Octopus("rocky", 22);   //creates and names the new octopus
        rocky.setWeight(210);       //rocky's weight
        rocky.setUtensil(sproon);       //rocky's favorite utensil

        System.out.println("Testing 'get' methods:");
        System.out.println(ocky.getName() + " weighs " +ocky.getWeight() 
            + " pounds\n" + "and is " + ocky.getAge() 
            + " years old. His favorite utensil is a "
            + ocky.getUtensil());

        System.out.println(ocky.getName() + "'s " + ocky.getUtensil() + " costs $"
            + ocky.getUtensil().getCost());
        System.out.println("Utensil's color: " + spat.getColor());

        // Use methods to change some values:

        ocky.setAge(20);
        ocky.setWeight(125);
        spat.setCost(15.99);
        spat.setColor("blue");

        System.out.println("\nTesting 'set' methods:");
        System.out.println(ocky.getName() + "'s new age: " + ocky.getAge());
        System.out.println(ocky.getName() + "'s new weight: " + ocky.getWeight());
        System.out.println("Utensil's new cost: $" + spat.getCost());
        System.out.println("Utensil's new color: " + spat.getColor());


        System.out.println(rocky.getName() + " weighs " +rocky.getWeight()
                + " pounds\n" + "and is " + rocky.getAge()
                + " years old. His favorite utensil is a "
                + rocky.getUtensil());

        System.out.println(rocky.getName() + "'s " + rocky.getUtensil() + " costs $"
                + rocky.getUtensil().getCost());
        System.out.println("Utensil's color: " + sproon.getColor());
    }
}
