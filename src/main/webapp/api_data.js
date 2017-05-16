define({ "api": [
  {
    "type": "get",
    "url": "/users",
    "title": "Request all users",
    "name": "GetAllUsers",
    "group": "User",
    "version": "0.0.1",
    "description": "<p>This request returns a list of all users in the database</p>",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Number",
            "optional": false,
            "field": "id",
            "description": "<p>Unique user id generated on sign up.</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "firstName",
            "description": "<p>First name of the user.</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "lastName",
            "description": "<p>Last name of the user.</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "email",
            "description": "<p>The users email used for sign up.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Success-Response:",
          "content": "{\n      \"id\": 0,\n      \"firstName\": \"Cristiana\",\n      \"lastName\": \"Man\",\n      \"email\": \"cma@mail.com\"\n },\n {\n      \"id\": 1,\n      \"firstName\": \"Andrei\",\n      \"lastName\": \"Atanasiu\",\n      \"email\": \"aa@mail.dk\"\n }",
          "type": "json"
        }
      ]
    },
    "error": {
      "examples": [
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 400 Bad Request\n{\n     \"error\": \"User table not found\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "src/main/java/dk/kea/soundsup/UserEndpoint.java",
    "groupTitle": "User"
  },
  {
    "type": "post",
    "url": "/users/{idToken}",
    "title": "Create a new user using a google sign in token.",
    "name": "getGoogleId",
    "group": "User",
    "version": "0.0.1",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "google",
            "description": "<p>ID obtained during sign up on the frontend.</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Number",
            "optional": false,
            "field": "id",
            "description": "<p>Unique ID generated on sign up.</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "firstName",
            "description": "<p>First Name of the user.</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "lastName",
            "description": "<p>Last name of the user.</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "email",
            "description": "<p>The users email used for sign up.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Success-Response:",
          "content": "HTTP/1.1 201 OK\n{\n<p>\n}",
          "type": "json"
        }
      ]
    },
    "error": {
      "examples": [
        {
          "title": "Error-Response",
          "content": "HTTP/1.1 406 Bad Request\n{\n\"error\": \"Invalid ID Token\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "src/main/java/dk/kea/soundsup/UserEndpoint.java",
    "groupTitle": "User"
  },
  {
    "type": "get",
    "url": "/users/{id}",
    "title": "Request specific user information",
    "name": "getUserById",
    "group": "User",
    "version": "0.0.1",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "id",
            "description": "<p>The unique ID of the user.</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Number",
            "optional": false,
            "field": "id",
            "description": "<p>Unique user id generated on sign up.</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "firstName",
            "description": "<p>First name of the user.</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "lastName",
            "description": "<p>Last name of the user.</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "email",
            "description": "<p>The users email used for sign up.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Success-Response:",
          "content": "{\n\"id\": 3,\n\"firstName\": \"Bat\",\n\"lastName\": \"Man\",\n\"email\": \"batman@super.org\";\n}",
          "type": "json"
        }
      ]
    },
    "error": {
      "examples": [
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 400 Bad Request\n{\n\"error\": \"User not found\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "src/main/java/dk/kea/soundsup/UserEndpoint.java",
    "groupTitle": "User"
  }
] });
