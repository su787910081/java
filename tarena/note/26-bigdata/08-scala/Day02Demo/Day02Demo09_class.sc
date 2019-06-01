object Day02Demo09_class {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  val p1 = new Person("tom", 10)                  //> p1  : Person = Person@3b6eb2ec
  // p1.setName("tom")
  // p1.setAge(10)
  p1.eat()                                        //> eat food
  p1                                              //> res0: Person = Person@3b6eb2ec
  
  val p2 = new Person("rose")                     //> p2  : Person = Person@1e643faf
  p2.getName()                                    //> res1: String = rose
  p2.getAge()                                     //> res2: Int = 0

  val p3 = new Person                             //> p3  : Person = Person@7f63425a
  p3.getName()                                    //> res3: String = ""
  p3.getAge()                                     //> res4: Int = 0
  
  val p4 = new Person(1)                          //> p4  : Person = Person@36d64342
  p4.getName()                                    //> res5: String = ""
  p4.getAge()                                     //> res6: Int = 1
  








}