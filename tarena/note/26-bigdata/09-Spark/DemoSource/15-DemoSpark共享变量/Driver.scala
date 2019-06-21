



object Driver {
    main {
        // map-side-join
        // 取出小表中出现的用户与大表关联后取出所需要的信息
        val people_info = sc.parallelize(Array(("1","tom"),("2","rose")))
            .collectAsMap()
        val student_all = sc.parallelize(Array(("1", "s1", "211"),
            ("1","s2",222),
            ('1","s3","233"),
            ("1","s2", "244")))

        // 将需要关联的小表进行广播
        val people_bc = sc.broadcast(people_info)
    }

}