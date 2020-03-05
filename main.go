package main

import (
	"database/sql"
	"fmt"

	"time"

	_ "github.com/go-sql-driver/mysql"
)

func main() {
	var db *sql.DB
	db = connectDB()
	insert(db)

}

func connectDB() (DB *sql.DB) {
	// var err error
	DB, _ = sql.Open("mysql", "root:root@tcp(127.0.0.1:3306)/test")
	//设置数据库最大连接数
	DB.SetConnMaxLifetime(100)
	//设置上数据库最大闲置连接数
	DB.SetMaxIdleConns(10)
	//验证连接
	if err := DB.Ping(); err != nil {
		fmt.Println("open database fail")
		return
	}
	fmt.Println("connnect success")
	return
}

func insert(db *sql.DB) {
	//开启事务
	tx, err := db.Begin()
	if err != nil {
		fmt.Println("tx fail")
		return
	}
	//准备sql语句
	stmt, err := tx.Prepare("INSERT INTO user VALUES (?, ?, ?, ?, ?, ?)")
	if err != nil {
		fmt.Println("Prepare fail")
		fmt.Println(err)
		return
	}
	//将参数传递到sql语句中并且执行
	res, err := stmt.Exec(2, "张三", 25, "男", time.Now().Local(), "15129582546", "辽宁省大连市")
	if err != nil {
		fmt.Println("Exec fail")
		fmt.Println(err)
		return
	}
	//将事务提交
	tx.Commit()
	//获得上一个插入自增的id
	fmt.Println(res.LastInsertId())
}
