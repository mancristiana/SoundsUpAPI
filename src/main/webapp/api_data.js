define({ "api": [
  {
    "type": "get",
    "url": "/posts",
    "title": "Get all posts",
    "name": "GetAllPosts",
    "group": "Post",
    "version": "0.0.1",
    "description": "<p>This request returns a list of all posts in the database</p>",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Number",
            "optional": false,
            "field": "post_id",
            "description": "<p>Unique post identifier</p>"
          },
          {
            "group": "Success 200",
            "type": "Number",
            "optional": false,
            "field": "user_id",
            "description": "<p>Unique user identifier generated on sign up.</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "track_id",
            "description": "<p>Unique identifier for the track corresponding to Spotify API Database.</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "description",
            "description": "<p>Content of the post</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Success Response",
          "content": "HTTP/1.1 200 OK\n[\n   {\n       \"postId\": 0,\n       \"userId\": 23,\n       \"trackId\": \"2eOn7OyFJ8ygzBrWGirpsB\",\n       \"description\": \"Some song\"\n   },\n   {\n       \"postId\": 1,\n       \"userId\": 32,\n       \"trackId\": \"1e2GBOtPyLu7iuNm4EvFKG\",\n       \"description\": \"Another description\"\n   },\n   {\n       \"postId\": 2,\n       \"userId\": 11,\n       \"trackId\": \"1zRFuPnqjBckEoOFliJyaI\",\n       \"description\": \"Such wow\"\n   },\n   {\n       \"postId\": 3,\n       \"userId\": 11,\n       \"trackId\": \"27HNh1cyB39ERqdpSjM2i1\",\n       \"description\": \"Amazing\"\n   }\n]",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "Error 4xx": [
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "400",
            "description": "<p>Bad request</p>"
          }
        ],
        "Error 5xx": [
          {
            "group": "Error 5xx",
            "optional": false,
            "field": "500",
            "description": "<p>Server has encountered a problem</p>"
          }
        ]
      }
    },
    "filename": "src/main/java/dk/kea/soundsup/PostEndpoint.java",
    "groupTitle": "Post"
  },
  {
    "type": "get",
    "url": "/posts/{id}",
    "title": "Get post",
    "name": "GetPost",
    "group": "Post",
    "version": "0.0.1",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "id",
            "description": "<p>Unique post identifier</p>"
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
            "field": "post_id",
            "description": "<p>Unique post identifier</p>"
          },
          {
            "group": "Success 200",
            "type": "Number",
            "optional": false,
            "field": "user_id",
            "description": "<p>Unique user identifier generated on sign up.</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "track_id",
            "description": "<p>Unique identifier for the track corresponding to Spotify API Database.</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "description",
            "description": "<p>Content of the post</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Success-Response:",
          "content": "{\n    \"post_id\": 3,\n    \"user_id\": 1,\n    \"track_id\": \"someIdb436427463mnj4n6k42l\",\n    \"description\": \"OMG This song is amazing!\"\n}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "Error 4xx": [
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "400",
            "description": "<p>Bad request</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "404",
            "description": "<p>Post not found</p>"
          }
        ],
        "Error 5xx": [
          {
            "group": "Error 5xx",
            "optional": false,
            "field": "500",
            "description": "<p>Server has encountered a problem</p>"
          }
        ]
      }
    },
    "filename": "src/main/java/dk/kea/soundsup/PostEndpoint.java",
    "groupTitle": "Post"
  },
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
          "content": "{\n\"id\": 0,\n\"firstName\": \"Cristiana\",\n\"lastName\": \"Man\",\n\"email\": \"cma@mail.com\"\n},\n{\n\"id\": 1,\n\"firstName\": \"Andrei\",\n\"lastName\": \"Atanasiu\",\n\"email\": \"aa@mail.dk\"\n}",
          "type": "json"
        }
      ]
    },
    "error": {
      "examples": [
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 400 Bad Request\n{\n\"error\": \"User table not found\"\n}",
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
