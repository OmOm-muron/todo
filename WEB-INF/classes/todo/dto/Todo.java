
package todo.dto;

import java.sql.*;
import java.text.*;
import java.util.*;

/**
 *
 * @author banba
 * 
 * TODO検索結果1行単位のDataTransferObject
 */
public class Todo {
    //フィールド：Todoの情報
    private int id;
    private String title;
    private String task;
    private Timestamp limitdate;
    private Timestamp lastupdate;
    private String userid;
    private int status;
    private String label;
    
    private String inputLimitdate;
    private String filename;
    
    //Getter
    public int getId() {
        return this.id;
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public String getTask() {
        return this.task;
    }
    
    public Timestamp getLimitdate() {
        return this.limitdate;
    }
    
    public Timestamp getLastupdate() {
        return this.lastupdate;
    }
    
    public String getUserid() {
        return this.userid;
    }
    
    public int getStatus() {
        return this.status;
    }
    
    public String getLabel() {
        return this.label;
    }
    
    public String getInputLimitdate() {
        return this.inputLimitdate;
    }
    
    public String getFilename() {
        return this.filename;
    }
    
    //Setter
    public void setId(int id) {
        this.id = id;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public void setTask(String task) {
        this.task = task;
    }
    
    public void setLimitdate(Timestamp limitdate) {
        this.limitdate = limitdate;
    }
    
    public void setLastupdate(Timestamp lastupdate) {
        this.lastupdate = lastupdate;
    }
    
    public void setUserid(String userid) {
        this.userid = userid;
    }
    
    public void setStatus(int status) {
        this.status = status;
    }
    
    public void setLabel(String label) {
        this.label = label;
    }
    
    public void setInputLimitdate(String inputLimitdate) {
        this.inputLimitdate = inputLimitdate;
    }
    
    public void setFilename(String filename) {
        this.filename = filename;
    }
}
