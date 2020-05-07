package com.example.tutor4tutor_src

import android.os.AsyncTask
import com.jcraft.jsch.Channel
import com.jcraft.jsch.ChannelSftp
import com.jcraft.jsch.JSch
import java.io.*
import java.sql.DriverManager.println

public class LoginTask : AsyncTask<String, Int, Boolean>() {
    override fun doInBackground(vararg operation: String): Boolean? {
        var jsch = JSch();
        val username = "wyang34";
        val remoteHost = "cheshire.cse.buffalo.edu"
        val password = "Hbxnywx1995.";
        var destPath = "/home/eslgrad/wyang34/Documents"
        val session = jsch.getSession(username, remoteHost, 22);
        session.setConfig("StrictHostKeyChecking", "no")
        session.setPassword(password);
        println("Establishing Connection...");
        session.connect();
        val channel: Channel = session.openChannel("sftp")
        channel.connect()
        val sftpChannel = channel as ChannelSftp
        sftpChannel.cd(destPath)
        var state:Boolean = false
        if(operation[0] == "login") {
            println("do login username is:")
            var loginstate = login(operation[1],operation[2],sftpChannel)
            if (loginstate == true) println("log in success")
            else println("log in fail")
            if (isContain("userstatus.txt",operation[1],sftpChannel))
                println("contain")
            else
                sftpChannel.put(ByteArrayInputStream((operation[1]+"\n").toByteArray()), "/home/eslgrad/wyang34/Documents/userstatus.txt", ChannelSftp.APPEND);
            print("not contain")
            state= loginstate

        }
        else if(operation[0]=="register")
        {
            var register:Boolean = register(operation[1],operation[2],sftpChannel)
            if (register == true) println("register success")
            else println("register fail")
            state= register
        }
        else if(operation[0] == "logout")
        {
            var tmps = logout(operation[1],sftpChannel)
            sftpChannel.put(ByteArrayInputStream(tmps.toByteArray()), "/home/eslgrad/wyang34/Documents/userstatus.txt", ChannelSftp.OVERWRITE);
        }
        else if(operation[0]=="reset")
        {
            var tmps = resetPassword(operation[1],operation[2],operation[3],sftpChannel)
            state = tmps
            //sftpChannel.put(ByteArrayInputStream(tmps.toByteArray()), "/home/eslgrad/wyang34/Documents/userstatus.txt", ChannelSftp.OVERWRITE);
        }

        //sftpChannel.put(ByteArrayInputStream(teststr.toByteArray()), destPath+filepath, ChannelSftp.OVERWRITE);
        //readFile(sftpChannel,destPath,filepath)
        return state
    }
    fun listMyFile(sftpChannel: ChannelSftp, destPath:String)
    {
        val filelist = sftpChannel.ls(destPath)
        for (i in filelist.indices) {
            val entry = filelist[i] as ChannelSftp.LsEntry
            println(entry.filename)
        }
    }
    fun login(username:String,password: String,sftpChannel: ChannelSftp):Boolean
    {
        var isContain = false
        val stream: InputStream = sftpChannel.get("/home/eslgrad/wyang34/Documents/userinfo.txt")
        try {
            val br = BufferedReader(InputStreamReader(stream))
            var line: String =""
            while (br.readLine().also({ line = it }) != null) {
                println(line)
                var user = line.split(',')
                if (user[0] == username && user[1] == password)
                {
                    UserStates.username = username
                    UserStates.state = true
                    isContain = true
                }
            }
        } catch (io: IOException) {
            System.out.println("Exception occurred during reading file from SFTP server due to " )
        } catch (e: Exception) {
            println("Exception occurred during reading file from SFTP server due to " + e.message)
            e.message
        }
        return isContain
    }
    fun logout(username:String,sftpChannel: ChannelSftp):String
    {
        val stream: InputStream = sftpChannel.get("/home/eslgrad/wyang34/Documents/userstatus.txt")
        var tmp:String = ""
        try {
            val br = BufferedReader(InputStreamReader(stream))
            var line: String =""
            while (br.readLine().also({ line = it }) != null) {
                if (line != username)
                {
                    tmp=tmp+line+"\n"
                }
            }
            return tmp

        } catch (io: IOException) {
            System.out.println("Exception occurred during reading file from SFTP server due to " )

        } catch (e: Exception) {
            println("Exception occurred during reading file from SFTP server due to " + e.message)
            e.message
        }
        return tmp
    }
    fun isContain(filename:String,data:String,sftpChannel: ChannelSftp):Boolean
    {
        val stream: InputStream = sftpChannel.get("/home/eslgrad/wyang34/Documents/"+filename)
        var iscontain = false
        try {
            val br = BufferedReader(InputStreamReader(stream))
            var line: String =""
            while (br.readLine().also({ line = it }) != null) {
                println(line)
                var w = line.split(',')
                for (item in w)
                {
                    if (item == data)
                    {
                        return !iscontain
                    }
                }
            }
        } catch (io: IOException) {
            System.out.println("Exception occurred during reading file from SFTP server due to " )

        } catch (e: Exception) {
            println("Exception occurred during reading file from SFTP server due to " + e.message)
            e.message
        }
        return iscontain
    }
    fun register(username:String,password:String,sftpChannel: ChannelSftp):Boolean
    {
        var isContain = false
        val stream: InputStream = sftpChannel.get("/home/eslgrad/wyang34/Documents/userinfo.txt")
        try {
            val br = BufferedReader(InputStreamReader(stream))
            var line: String =""
            while (br.readLine().also({ line = it }) != null) {
                println(line)
                var user = line.split(',')
                if (user[0] == username)
                {
                    isContain = true
                }
            }
        } catch (io: IOException) {

        } catch (e: Exception) {
            println("Exception occurred during reading file from SFTP server due to " + e.message)
            e.message
        }
        if(!isContain) {
            var u: String = username + ","+password+"\n"
            sftpChannel.put(
                    ByteArrayInputStream(u.toByteArray()),
                    "/home/eslgrad/wyang34/Documents/userinfo.txt",
                    ChannelSftp.APPEND
            )
        }
        return !isContain
    }
    fun resetPassword(username:String,password:String,newpassword:String,sftpChannel: ChannelSftp):Boolean
    {
        var isContain = false
        var orfile:String = ""
        val stream: InputStream = sftpChannel.get("/home/eslgrad/wyang34/Documents/userinfo.txt")
        try {
            val br = BufferedReader(InputStreamReader(stream))
            var line: String =""
            while (br.readLine().also({ line = it }) != null) {
                println(line)
                var user = line.split(',')
                if (user[0] == username && user[1] == password)
                {
                    isContain = true
                }
                else{
                    orfile+=line+"\n"
                }
            }
        } catch (io: IOException) {

        } catch (e: Exception) {
            println("Exception occurred during reading file from SFTP server due to " + e.message)
            e.message
        }
        if(isContain)
        {
            var u: String =orfile + username + ","+newpassword+"\n"
            sftpChannel.put(
                    ByteArrayInputStream(u.toByteArray()),
                    "/home/eslgrad/wyang34/Documents/userinfo.txt",
                    ChannelSftp.OVERWRITE
            )
        }
        return isContain
    }

    fun readFile(sftpChannel: ChannelSftp, path:String, filename:String)
    {
        val stream: InputStream = sftpChannel.get(path+filename)
        try {
            val br = BufferedReader(InputStreamReader(stream))
            var line: String =""
            while (br.readLine().also({ line = it }) != null) {

                println(line)
            }
        } catch (io: IOException) {

        } catch (e: Exception) {
            println("Exception occurred during reading file from SFTP server due to " + e.message)
            e.message
        }
    }
}