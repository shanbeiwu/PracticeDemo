package main

import (
	"database/sql"
	"fmt"

	"time"

	_ "github.com/go-sql-driver/mysql"
)

//定义数据库连接参数
var (
	userName  string = "root"
	password  string = "root"
	ipAddrees string = "127.0.0.1"
	port      int    = 3306
	dbName    string = "test"
	charset   string = "utf8"
)

// DB 数据库连接
var DB *sql.DB

func main() {
	//var db *sql.DB
	ConnectDB()
	//insert(db)
	QueryData()

}

//ConnectDB 是获取数据库连接
func ConnectDB() {
	// var err error
	dsn := fmt.Sprintf("%s:%s@tcp(%s:%d)/%s?charset=%s", userName, password, ipAddrees, port, dbName, charset)
	DB, _ = sql.Open("mysql", dsn)
	//设置数据库最大连接数
	DB.SetConnMaxLifetime(100)
	//设置上数据库最大闲置连接数
	DB.SetMaxIdleConns(10)
	//验证连接
	if err := DB.Ping(); err != nil {
		fmt.Println("数据库连接失败：", err)
		return
	}
	fmt.Println("数据库连接成功")
}

// Insert 插入数据 db *sql.DB
func Insert() {
	//开启事务
	tx, err := DB.Begin()
	if err != nil {
		fmt.Println("tx fail")
		return
	}
	//准备sql语句
	stmt, err := tx.Prepare("INSERT INTO user (name, age, sex, birth, phone, address) VALUES (?, ?, ?, ?, ?,?)")
	if err != nil {
		fmt.Println("Prepare fail")
		fmt.Println(err)
		return
	}
	//将参数传递到sql语句中并且执行
	res, err := stmt.Exec("赵柳", 25, "男", time.Now(), "15129582546", "辽宁省大连市")
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

// QueryData 查询
func QueryData() {
	rows, err := DB.Query("select userID, `name`, age, sex, phone, address from user")
	if err != nil {
		fmt.Printf("查询失败, 错误:[%v]", err.Error())
		return
	}
	for rows.Next() {
		// 定义接收变量
		var userID, age int
		var name, sex, phone, address string
		err := rows.Scan(&userID, &name, &age, &sex, &phone, &address)
		if err != nil {
			fmt.Printf("接收查询失败, 错误:[%v]", err.Error())
		}
		fmt.Println(userID, name, age, sex, phone, address)
	}
	rows.Close()
}
