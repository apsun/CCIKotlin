package com.crossbowffs.ccikotlin.chapter3

import com.crossbowffs.ccikotlin.utils.doAssert
import java.util.*

abstract class Animal(val name: String)
class Cat(name: String) : Animal(name)
class Dog(name: String) : Animal(name)

/**
 * Represents a first-in-first-out queue of cats and dogs.
 * Clients can choose to adopt the oldest cat/dog (choosing
 * an animal returns the oldest of that type, otherwise the
 * oldest animal overall is returned).
 */
class AnimalShelter() {
    private class AnimalHolder<out T: Animal>(val animal: T, val counter: Int)

    private var counter = 0
    private val cats = LinkedList<AnimalHolder<Cat>>()
    private val dogs = LinkedList<AnimalHolder<Dog>>()

    fun enqueue(animal: Animal) {
        if (animal is Cat) {
            cats.offerLast(AnimalHolder(animal, ++counter))
        } else if (animal is Dog) {
            dogs.offerLast(AnimalHolder(animal, ++counter))
        }
    }

    fun dequeueCat() = cats.pollFirst()?.animal

    fun dequeueDog() = dogs.pollFirst()?.animal

    fun dequeueAny(): Animal? {
        val oldestCat = cats.peekFirst()
        val oldestDog = dogs.peekFirst()
        if (oldestCat == null) return dequeueDog()
        if (oldestDog == null) return dequeueCat()
        if (oldestCat.counter < oldestDog.counter) {
            return dequeueCat()
        } else {
            return dequeueDog()
        }
    }
}

fun main(args: Array<String>) {
    AnimalShelter().apply {
        doAssert(dequeueCat() == null)
        doAssert(dequeueDog() == null)
        doAssert(dequeueAny() == null)
    }
    AnimalShelter().apply {
        enqueue(Cat("Chocola"))
        enqueue(Cat("Vanilla"))
        enqueue(Dog("Coco"))
        doAssert(dequeueAny()!!.name == "Chocola")
        doAssert(dequeueDog()!!.name == "Coco")
        doAssert(dequeueDog() == null)
        doAssert(dequeueCat()!!.name == "Vanilla")
        doAssert(dequeueAny() == null)
    }
}
