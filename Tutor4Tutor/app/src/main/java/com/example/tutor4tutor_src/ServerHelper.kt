package com.example.tutor4tutor_src

import android.os.AsyncTask
import com.jcraft.jsch.Channel
import com.jcraft.jsch.ChannelSftp
import com.jcraft.jsch.JSch
import org.json.JSONObject
import java.io.*
import java.sql.DriverManager
public class ServerHelper : AsyncTask<String, Int, List<String>>() {
    //"operation"""data"
    override fun doInBackground(vararg params: String): List<String> {
        var jsch = JSch();
        val username = "wyang34";
        val password = "Hbxnywx1995.";
        val remoteHost = "cheshire.cse.buffalo.edu"
        var destPath = "/home/eslgrad/wyang34/Documents"
        val session = jsch.getSession(username, remoteHost, 22);
        session.setConfig("StrictHostKeyChecking", "no")
        session.setPassword(password);
        DriverManager.println("Establishing Connection...");
        session.connect();
        val channel: Channel = session.openChannel("sftp")
        channel.connect()
        val sftpChannel = channel as ChannelSftp
        sftpChannel.cd(destPath)
        var data:List<String> = listOf()
        var isContain = false
        if(params[0] == "get")
        {
            var userpath = params[1]
            try {
                var stream = sftpChannel.get(destPath + "/" +userpath)
                val br = BufferedReader(InputStreamReader(stream))
                var line: String = ""
                while (br.readLine().also({ line = it }) != null) {
                    DriverManager.println(line)
                    data+=line
                }
            } catch (io: IOException) {
                System.out.println("Exception occurred during reading file from SFTP server due to ")
            } catch (e: Exception) {
                DriverManager.println("Exception occurred during reading file from SFTP server due to " + e.message)
                e.message
            }
        }
        if (params[0] == "getComment") {
            var userpath = UserStates.selectUsername+"comment"
            val filelist = sftpChannel.ls(destPath)
            for (i in filelist.indices) {
                val entry = filelist[i] as ChannelSftp.LsEntry
                val file = entry.filename
                if(file == userpath)
                {
                    isContain = true
                }
            }
            if (!isContain)
            {
                sftpChannel.mkdir(userpath)
                sftpChannel.put(destPath+"/"+userpath+"/"+"comment.txt")
            }
            var stream = sftpChannel.get(destPath + "/" +userpath+"/"+"/comment.txt")
            try {
                val br = BufferedReader(InputStreamReader(stream))
                var line: String = ""
                while (br.readLine().also({ line = it }) != null) {
                    DriverManager.println(line)
                    data+=line
                }
            } catch (io: IOException) {
                System.out.println("Exception occurred during reading file from SFTP server due to ")
            } catch (e: Exception) {
                DriverManager.println("Exception occurred during reading file from SFTP server due to " + e.message)
                e.message
            }
        }
        if (params[0] =="set")
        {
            val filelist = sftpChannel.ls(destPath)
            try {
                var userpath = UserStates.selectUsername+"comment"
                for (i in filelist.indices) {
                    val entry = filelist[i] as ChannelSftp.LsEntry
                    val file = entry.filename
                    if(file == UserStates.username+"comment")
                    {
                        sftpChannel.put(ByteArrayInputStream((UserStates.username+","+params[1]+"\n").toByteArray()),destPath+"/"+userpath+"/"+"comment.txt",ChannelSftp.APPEND)
                        return listOf()
                    }
                }
                sftpChannel.mkdir(userpath)
                sftpChannel.put(destPath+"/"+userpath+"/"+"comment.txt")
                sftpChannel.put(ByteArrayInputStream((UserStates.username+","+params[1]+"\n").toByteArray()),destPath+"/"+userpath+"/"+"comment.txt",ChannelSftp.APPEND)
                return listOf()
            } catch (io: IOException) {

            } catch (e: Exception) {
                DriverManager.println("Exception occurred during reading file from SFTP server due to " + e.message)
                e.message
            }

        }
        return data
    }


}

public enum class ServerOperation
{
    GetComment,GetUserInfo,SetComment,SetUserInfo
}