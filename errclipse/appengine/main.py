import sys
import os
import logging
import json
import wsgiref.handlers
from django.utils import simplejson as json
from array import *
from google.appengine.ext import webapp
from google.appengine.api import users
from google.appengine.ext import db
import webapp2

class Error(db.Model):
    error_desc=db.StringProperty()
    level_key=db.StringProperty()
    error_id=db.IntegerProperty()
    solution_count=db.IntegerProperty()
    
class Language(db.Model):
    language_id=db.IntegerProperty()
    language_desc=db.StringProperty()
    solution_count=db.IntegerProperty()

class Library(db.Model):
    library_desc=db.StringProperty()
    level_key=db.StringProperty()
    library_id=db.IntegerProperty()
    solution_count=db.IntegerProperty()

class Method(db.Model):
    method_desc=db.StringProperty()
    level_key=db.StringProperty()
    method_id=db.IntegerProperty()
    solution_count=db.IntegerProperty()

class Solution(db.Model):
    level_key=db.StringProperty()
    solution_id=db.IntegerProperty()
    solution_desc=db.StringProperty()
    global_score=db.FloatProperty()
    local_score=db.IntegerProperty()

class InsertHandler(webapp.RequestHandler):
    def get(self):
        query=sys.stdin.read()
        try:
            obj=json.loads(query)
        except:
            self.response.out.write("POST - json error")
            return -1
        self.insert(obj)

    def post(self):
        query=sys.stdin.read()
        try:
            obj=json.loads(query)
        except:
            self.response.out.write("POST - json error")
            return -1
        self.insert(obj)
       

    def insert(self,obj):
        if(obj["targetTable"]=="error"):
            self.insert_error(obj["values"])
        elif(obj["targetTable"]=="language"):
            self.insert_language(obj["values"])
        elif(obj["targettable"]=="library"):
            self.insert_library(obj["values"])
        elif(obj["targetTable"]=="method"):
            self.insert_method(obj["values"])
        elif(obj["targetTable"]=="solution"):
            self.insert_solution(obj["values"])
                   
    def insert_error(self,obj):
        all_err=db.Query(Error)
        que=all_err.filter('error_desc =',obj["error_desc"])
        res=que.fetch(limit=1)
        if(len(res)>0):
            self.response.out.write("duplicated error_desc")
            return -1
        que=all_err.filter('error_id =',obj["error_id"])
        res=que.fetch(limit=1)
        if(len(res)>0):
            self.response.out.write("duplicated error_id")
            return -1

        new=Error(error_desc=obj["error_desc"],level_key=obj["level_key"],error_id=obj["error_id"],solution_count=obj["solution_count"])
        new.put();

    def insert_language(self,obj):
        all_lang=db.Query(Language)
        que=all_lang.filter('language_desc =',obj["language_desc"])
        res=que.fetch(limit=1)
        if(len(res)>0):
            self.response.out.write("duplicated language_desc")
            return -1
        que=all_lang.filter('language_id =',obj["language_id"])
        res=que.fetch(limit=1)
        if(len(res)>0):
            self.response.out.write("duplicated language_id")
            return -1
        new=Language(language_id=obj["language_id"],language_desc=obj["language_desc"],solution_count=obj["solution_count"])
        new.put();
    
    def insert_library(self,obj):
        all_lib=db.Query(Library)
        que=all_lib.filter('library_desc =',obj["library_desc"])
        res=que.fetch(limit=1)
        if(len(res)>0):
            self.response.out.write("duplicated library_desc")
            return -1
        que=all_lib.filter('library_id =',obj["library_id"])
        res=que.fetch(limit=1)
        if(len(res)>0):
            self.response.out.write("duplicated library_id")
            return -1
        new=Library(library_desc=obj["library_desc"],level_key=obj["level_key"],library_id=obj["library_id"],solution_count=obj["solution_count"])
        new.put();

    def insert_method(self,obj):
        all_method=db.Query(Method)
        que=all_method.filter('method_desc =',obj["method_desc"])
        res=que.fetch(limit=1)
        if(len(res)>0):
            self.response.out.write("duplicated method_desc")
            return -1
        que=all_method.filter('method_id =',obj["method_id"])
        res=que.fetch(limit=1)
        if(len(res)>0):
            self.response.out.write("duplicated method_id")
            return -1
        new=Method(method_desc=obj["method_desc"],level_key=obj["level_key"],method_id=obj["method_id"],solution_count=obj["solution_count"])
        new.put();
        
    def insert_solution (self,obj):
        all_solution=db.Query(Solution)
        que=all_solution.filter('solution_id =',obj["solution_id"])
        res=que.fetch(limit=1)
        if(len(res)>0):
            self.response.out.write("duplicated solution_id")
            return -1
        new=Solution(level_key=obj["level_key"],solution_id=obj["solution_id"],solution_desc=obj["solution_desc"],global_score=obj["global_score"],local_score=obj["local_score"])
        new.put();
 
class GetDescListErrorHandler(webapp.RequestHandler):
    def get(self):
        self.get_desc_list()
    def post(self):
        self.get_desc_list()
    def get_desc_list(self):
        que = db.GqlQuery("SELECT error_desc FROM Error")
        list=[]
        for item in que.run():
            newitem={}
            newitem["error_desc"]=item.error_desc
            list.append(newitem)
        self.response.out.write(json.dumps(list))
        

class GetDescListLanguageHandler(webapp.RequestHandler):
    def get(self):
        self.get_desc_list()
    def post(self):
        self.get_desc_list()
    def get_desc_list(self):
        que = db.GqlQuery("SELECT language_desc FROM Language")
        list=[]
        for item in que.run():
            newitem={}
            newitem["language_desc"]=item.language_desc
            list.append(newitem)
        self.response.out.write(json.dumps(list))

class GetDescListLibraryHandler(webapp.RequestHandler):
    def get(self):
        self.get_desc_list()
    def post(self):
        self.get_desc_list()
    def get_desc_list(self):
        que = db.GqlQuery("SELECT library_desc FROM Library")
        list=[]
        for item in que.run():
            newitem={}
            newitem["library_desc"]=item.library_desc
            list.append(newitem)
        self.response.out.write(json.dumps(list))
        
class GetDescListMethodHandler(webapp.RequestHandler):
    def get(self):
        self.get_desc_list()
    def post(self):
        self.get_desc_list()
    def get_desc_list(self):
        que = db.GqlQuery("SELECT method_desc FROM Method")
        list=[]
        for item in que.run():
            newitem={}
            newitem["method_desc"]=item.method_desc
            list.append(newitem)
        self.response.out.write(json.dumps(list))

class SelectAllErrorHandler(webapp.RequestHandler):
    def get(self):
        self.select()
    def post(self):
        self.select()
    def select(self):
        que = db.GqlQuery("SELECT * FROM Error")
        list=[]
        for item in que.run():
            newitem={}
            newitem["error_desc"]=item.error_desc
            newitem["level_key"]=item.level_key
            newitem["error_id"]=item.error_id
            newitem["solution_count"]=item.solution_count
            list.append(newitem)
        self.response.out.write(json.dumps(list))
    
class SelectAllLanguageHandler(webapp.RequestHandler):
    def get(self):
        self.select()
    def post(self):
        self.select()
    def select(self):
        que = db.GqlQuery("SELECT * FROM Language")
        list=[]
        for item in que.run():
            newitem={}
            newitem["language_id"]=item.language_id
            newitem["language_desc"]=item.language_desc
            newitem["solution_count"]=item.solution_count
            list.append(newitem)

        self.response.out.write(json.dumps(list))

class SelectAllLibraryHandler(webapp.RequestHandler):
    def get(self):
        self.select()
    def post(self):
        self.select()
    def select(self):
        que = db.GqlQuery("SELECT * FROM Library")
        list=[]
        for item in que.run():
            newitem={}
            newitem["library_desc"]=item.library_desc
            newitem["level_key"]=item.level_key
            newitem["library_id"]=item.library_id
            newitem["solution_count"]=item.solution_count
            list.append(newitem)
        self.response.out.write(json.dumps(list))

class SelectAllMethodHandler(webapp.RequestHandler):
    def get(self):
        self.select()
    def post(self):
        self.select()
    def select(self):
        que = db.GqlQuery("SELECT * FROM Method")
        list=[]
        for item in que.run():
            newitem={}
            newitem["method_desc"]=item.method_desc
            newitem["level_key"]=item.level_key
            newitem["method_id"]=item.method_id
            newitem["solution_count"]=item.solution_count
            list.append(newitem)
        self.response.out.write(json.dumps(list))

class SelectAllSolutionHandler(webapp.RequestHandler):
    def get(self):
        self.select()
    def post(self):
        self.select()
    def select(self):
        que = db.GqlQuery("SELECT * FROM Solution")
        list=[]
        for item in que.run():
            newitem={}
            newitem["level_key"]=item.level_key
            newitem["solution_id"]=item.solution_id
            newitem["solution_desc"]=item.solution_desc
            newitem["global_score"]=item.global_score
            newitem["local_score"]=item.local_score
            list.append(newitem)
        self.response.out.write(json.dumps(list))

class SelectHandler(webapp.RequestHandler):
    def get(self):
        query=sys.stdin.read()
        obj=json.loads(query)
        self.select(obj)
    def post(self):
        query=sys.stdin.read()
        obj=json.loads(query)
        self.select(obj)
    def select(obj):
        cond_count=obj["cond_count"]
        cond_type=obj["cond_type"]
        arr_cond=obj["cond"]
        flag=0
        for obj_cond in arr_cond:
            #cond_value_type = STRING, INTEGER, FLOAT

            if(obj["cond_type"]=="eq"):
                cond_type="="
            elif(obj["cond_type"]=="neq"):
                cond_type="!="
            elif(obj["cond_type"]=="gt"):
                cond_type=">"
            elif(obj["cond_type"]=="ge"):
                cond_type=">="
            elif(obj["cond_type"]=="lt"):
                cond_type="<"
            elif(obj["cond_type"]=="le"):
                cond_type="<="
            else:
                cond_type="="
            
            if(flag==0):
                flag=1
                cond_coulmn=obj_cond["cond_coulmn"]
                cond_value=obj_cond["cond_value"]
                sql_cond=cond_coulmn+" "+obj_cond["cond_type"]+" '"+cond_value+"'"
            else:
                cond_coulmn=obj_cond["cond_coulmn"]
                cond_value=obj_cond["cond_value"]
                sql_cond=" "+cond_form+" "+cond_coulmn+" "+obj_cond["cond_type"]+" '"+cond_value+"'"
            
        que = db.GqlQuery("SELECT * FROM :1 " +
                "WHERE :2"+
                "ORDER BY :3 :4 LIMIT :5, :6"
                ,obj["table_name"],sql_cond,obj["ord_column"],obj["ord_type"],obj["offset"],obj["count"])

        for item in que.run(limit=obj["count"]):
            self.response.out.write(json.dumps(item))
            
        que=db.Query(ErrInfo)
        que=que.filter('where =',"where")
        res=que.fetch(limit=10)
        self.response.out.write(res[0].when)

class Update(webapp.RequestHandler):
    def get(self):
        query=sys.stdin.read()
        obj=json.loads(query)
    def post(self):
        query=sys.stdin.read()
        obj=json.loads(query)

class GetUnsynchedList(webapp.RequestHandler):
    def get(self):
        query=sys.stdin.read()
        obj=json.loads(query)
        self.get_unsynched_list(obj)
    def post(self):
        query=sys.stdin.read()
        obj=json.loads(query)
        self.get_unsynched_list(obj)
    def get_unsynched_list(obj):
        if(obj["table_name"]=="error"):
            desc="error_desc"
        elif(obj["table_name"]=="language"):
            desc="language_desc"
        elif(obj["table_name"]=="library"):
            desc="library_desc"
        elif(obj["table_name"]=="method"):
            desc="method_desc"

        que = db.GqlQuery("SELECT * FROM :1 " +
                "WHERE not in { :2 }"
                ,obj["table_name"],obj["list"])

        list=[]
        for item in que.run():
            list.append(item.language_desc)
        
        self.response.out.write(json.dumps(list))

def main():
    application = webapp.WSGIApplication([
     ('/selectall/error', SelectAllErrorHandler),
     ('/selectAll/error', SelectAllErrorHandler),
     ('/SelectAll/error', SelectAllErrorHandler),
     ('/selectall/error/', SelectAllErrorHandler),
     ('/selectAll/error/', SelectAllErrorHandler),
     ('/SelectAll/error/', SelectAllErrorHandler),
     ('/selectall/language', SelectAllLanguageHandler),
     ('/selectAll/language', SelectAllLanguageHandler),
     ('/SelectAll/language', SelectAllLanguageHandler),
     ('/selectall/language/', SelectAllLanguageHandler),
     ('/selectAll/language/', SelectAllLanguageHandler),
     ('/SelectAll/language/', SelectAllLanguageHandler),
     ('/selectall/library', SelectAllLibraryHandler),
     ('/selectAll/library', SelectAllLibraryHandler),
     ('/SelectAll/library', SelectAllLibraryHandler),
     ('/selectall/library/', SelectAllLibraryHandler),
     ('/selectAll/library/', SelectAllLibraryHandler),
     ('/SelectAll/library/', SelectAllLibraryHandler),
     ('/selectall/method', SelectAllMethodHandler),
     ('/selectAll/method', SelectAllMethodHandler),
     ('/SelectAll/method', SelectAllMethodHandler),
     ('/selectall/method/', SelectAllMethodHandler),
     ('/selectAll/method/', SelectAllMethodHandler),
     ('/SelectAll/method/', SelectAllMethodHandler),
     ('/selectall/solution', SelectAllSolutionHandler),
     ('/selectAll/solution', SelectAllSolutionHandler),
     ('/SelectAll/solution', SelectAllSolutionHandler),
     ('/selectall/solution/', SelectAllSolutionHandler),
     ('/selectAll/solution/', SelectAllSolutionHandler),
     ('/SelectAll/solution/', SelectAllSolutionHandler),
     ('/insert', InsertHandler),
     ('/Insert', InsertHandler),
     ('/insert/', InsertHandler),
     ('/Insert/', InsertHandler),
     ('/getdesclist/error', GetDescListErrorHandler),
     ('/getDescList/error', GetDescListErrorHandler),
     ('/GetDescList/error', GetDescListErrorHandler),
     ('/getdesclist/error/', GetDescListErrorHandler),
     ('/getDescList/error/', GetDescListErrorHandler),
     ('/GetDescList/error/', GetDescListErrorHandler),
     ('/getdesclist/language', GetDescListLanguageHandler),
     ('/getDescList/language', GetDescListLanguageHandler),
     ('/GetDescList/language', GetDescListLanguageHandler),
     ('/getdesclist/language/', GetDescListLanguageHandler),
     ('/getDescList/language/', GetDescListLanguageHandler),
     ('/GetDescList/language/', GetDescListLanguageHandler),
     ('/getdesclist/library', GetDescListLibraryHandler),
     ('/getDescList/library', GetDescListLibraryHandler),
     ('/GetDescList/library', GetDescListLibraryHandler),
     ('/getdesclist/library/', GetDescListLibraryHandler),
     ('/getDescList/library/', GetDescListLibraryHandler),
     ('/GetDescList/library/', GetDescListLibraryHandler),
     ('/getdesclist/method', GetDescListMethodHandler),
     ('/getDescList/method', GetDescListMethodHandler),
     ('/GetDescList/method', GetDescListMethodHandler),
     ('/getdesclist/method/', GetDescListMethodHandler),
     ('/getDescList/method/', GetDescListMethodHandler),
     ('/GetDescList/method/', GetDescListMethodHandler)],
     debug=True)
    wsgiref.handlers.CGIHandler().run(application)

if __name__ == '__main__':
    main()


