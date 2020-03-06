package main

import (
	"fmt"
	"time"
)

type student struct {
	name, phone, sex, address string
	age                       int
}

// Player 玩家
type Player struct {
	Name        string
	HealthPoint int
	MagicPoint  int
}

func main() {
	var stu student
	stu.name = "张三"

	tank := new(Player)
	tank.Name = "Canon"
	tank.HealthPoint = 300

	now := time.Now()
	fmt.Println(now)
	year := now.Year()     // 年
	month := now.Month()   // 月
	day := now.Day()       //日
	hour := now.Hour()     //小时
	minute := now.Minute() //分钟
	second := now.Second() //秒
	date := fmt.Sprintf("%d-%02d-%02d %02d:%02d:%02d\n", year, month, day, hour, minute, second)
	fmt.Println(date)

	nowStamp := uts()
	timeObj := StampToDate(nowStamp)
	year2 := timeObj.Year()     //年
	month2 := timeObj.Month()   //月
	day2 := timeObj.Day()       //日
	hour2 := timeObj.Hour()     //小时
	minute2 := timeObj.Minute() //分钟
	second2 := timeObj.Second() //秒
	fmt.Printf("%d-%02d-%02d %02d:%02d:%02d\n", year2, month2, day2, hour2, minute2, second2)

	// 获取星期
	fmt.Println(now.Weekday().String())

	// 时间格式化 诞生时间 2006 年 1 月 2 号 15 点 04 分 05 秒。
	fmt.Println(now.Format("2006/01/02 15:04:05"))

	StringToDate()
}

// uts 获取时间戳
func uts() int64 {
	now := time.Now()
	var timestamp1 int64
	timestamp1 = now.Unix() // 时间戳
	var timestamp2 int64
	timestamp2 = now.UnixNano() // 纳秒时间戳
	fmt.Printf("现在的时间戳：%v\n", timestamp1)
	fmt.Printf("现在的纳秒时间戳：%v\n", timestamp2)
	return timestamp1
}

// StampToDate 将时间戳转换为时间格式
func StampToDate(timestamp int64) time.Time {
	timeObj := time.Unix(timestamp, 0)
	return timeObj
}

// StringToDate 字符串转时间
func StringToDate() {
	var layout string = "2006-01-02 15:04:05"
	var timeStr string = "2020-03-06 17:15:10"
	timeObj1, _ := time.Parse(layout, timeStr)
	fmt.Printf("类型1%T\n", timeObj1)
	fmt.Println(timeObj1)
	timeObj2, _ := time.ParseInLocation(layout, timeStr, time.Local)
	fmt.Printf("类型2%T\n", timeObj2)
	fmt.Println(timeObj2)
}
