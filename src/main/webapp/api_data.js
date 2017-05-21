define({ "api": [
  {
    "type": "post",
    "url": "/posts",
    "title": "Create Post",
    "name": "CreatePost",
    "group": "Posts",
    "version": "1.0.0",
    "description": "<p>This request creates a new post by using the json body provided. For consistency the json should include the parameters specified below. A postId field is generated automatically and returned in the response once the post has been saved.</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "description",
            "description": "<p>Content of the post</p>"
          },
          {
            "group": "Parameter",
            "type": "User",
            "optional": false,
            "field": "user",
            "description": "<p>object representing the post author. Please refer to the user endpoint for the structure of the user.</p>"
          },
          {
            "group": "Parameter",
            "type": "Track",
            "optional": false,
            "field": "track",
            "description": "<p>object shared within the post</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Post Example",
          "content": "{\n    \"id\": 0,\n    \"description\": \"This is a description example!\",\n    \"user\": {\n        \"id\": 3\n    },\n    \"track\": {\n        \"spotifyId\": \"5anCkDvJ17aznvK5TED5uo\",\n        \"name\": \"Hail to the King\",\n        \"previewUrl\": \"https://p.scdn.co/mp3-preview/7a8932458d8ea00a425b629f43c4d44af0c9a029?cid=null\",\n        \"album\": {\n            \"id\": \"0ks45m1bsP2JsZpM5D2FFA\",\n            \"name\": \"Hail to the King\",\n            \"imageUrl\": \"https://i.scdn.co/image/d6fef16190f1516d0efe91c0d1bc6f28d8aa8865\"\n        },\n        \"artist\": {\n            \"id\": \"0ks45m1bsP2JsZpM5D2FFA\",\n            \"name\": \"Avenged Sevenfold\"\n        },\n        \"externalUrls\": {\n            \"spotify\": \"https://open.spotify.com/album/0ks45m1bsP2JsZpM5D2FFA\"\n        }\n    }\n}",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "Success 2xx": [
          {
            "group": "Success 2xx",
            "optional": false,
            "field": "201",
            "description": "<p>Post Created</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Success Response",
          "content": "HTTP/1.1 201 Created\n{\n  \"status\": 201,\n  \"message\": \"Post with id = {id} was sucessfully inserted.\"\n}",
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
            "description": "<p>Bad Request</p>"
          }
        ],
        "Error 5xx": [
          {
            "group": "Error 5xx",
            "optional": false,
            "field": "500",
            "description": "<p>Internal Server Error</p>"
          }
        ]
      }
    },
    "filename": "src/main/java/dk/kea/soundsup/PostEndpoint.java",
    "groupTitle": "Posts"
  },
  {
    "type": "delete",
    "url": "/post/{id}",
    "title": "Delete Post",
    "name": "DeletePost",
    "group": "Posts",
    "version": "1.0.0",
    "description": "<p>This request deletes an existing post with the id specified in the request URL.</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "id",
            "description": "<p>The id of the Post</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 2xx": [
          {
            "group": "Success 2xx",
            "optional": false,
            "field": "202",
            "description": "<p>Accepted</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Success-Response:",
          "content": "HTTP/1.1 202 Accepted\n{\n     \"status\" : 202,\n     \"message\" : \"Post was successfully deleted\"\n}",
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
            "field": "404",
            "description": "<p>Post Not Found</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "400",
            "description": "<p>Bad Request</p>"
          }
        ],
        "Error 5xx": [
          {
            "group": "Error 5xx",
            "optional": false,
            "field": "500",
            "description": "<p>Internal Server Error</p>"
          }
        ]
      }
    },
    "filename": "src/main/java/dk/kea/soundsup/PostEndpoint.java",
    "groupTitle": "Posts"
  },
  {
    "type": "get",
    "url": "/posts",
    "title": "Get all posts",
    "name": "GetAllPosts",
    "group": "Posts",
    "version": "1.0.0",
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
            "type": "String",
            "optional": false,
            "field": "description",
            "description": "<p>Content of the post</p>"
          },
          {
            "group": "Success 200",
            "type": "User",
            "optional": false,
            "field": "user",
            "description": "<p>object representing the post author. Please refer to the user endpoint for the structure of the user.</p>"
          },
          {
            "group": "Success 200",
            "type": "Track",
            "optional": false,
            "field": "track",
            "description": "<p>object shared within the post</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "SuccessResponse",
          "content": "HTTP/1.1 200 OK\n[\n     {\n         \"id\": 0,\n         \"description\": \"Wow amazing song\",\n         \"user\": {\n             \"id\": 3,\n             \"name\": \"Andrei\",\n             \"email\": \"and@man.com\"\n         },\n         \"track\": {\n             \"id\": 1,\n             \"spotifyId\": \"5anCkDvJ17aznvK5TED5uo\",\n             \"name\": \"Hail to the King\",\n             \"previewUrl\": \"https://p.scdn.co/mp3-preview/7a8932458d8ea00a425b629f43c4d44af0c9a029?cid=null\",\n             \"album\": {\n                 \"id\": \"0ks45m1bsP2JsZpM5D2FFA\",\n                 \"name\": \"Hail to the King\",\n                 \"imageUrl\": \"https://i.scdn.co/image/d6fef16190f1516d0efe91c0d1bc6f28d8aa8865\"\n             },\n             \"artist\": {\n                 \"id\": \"0ks45m1bsP2JsZpM5D2FFA\",\n                 \"name\": \"Avenged Sevenfold\"\n             },\n             \"externalUrls\": {\n                 \"spotify\": \"https://open.spotify.com/album/0ks45m1bsP2JsZpM5D2FFA\"\n             }\n         }\n    },\n    ...\n]",
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
    "groupTitle": "Posts"
  },
  {
    "type": "get",
    "url": "/posts/{id}",
    "title": "Get post",
    "name": "GetPost",
    "group": "Posts",
    "version": "1.0.0",
    "description": "<p>This request returns the post with the specified id</p>",
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
            "type": "String",
            "optional": false,
            "field": "description",
            "description": "<p>Content of the post</p>"
          },
          {
            "group": "Success 200",
            "type": "User",
            "optional": false,
            "field": "user",
            "description": "<p>object representing the post author. Please refer to the user enpoint for the structure of the user.</p>"
          },
          {
            "group": "Success 200",
            "type": "Track",
            "optional": false,
            "field": "track",
            "description": "<p>object shared within the post</p>"
          }
        ]
      }
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
    "groupTitle": "Posts"
  },
  {
    "type": "put",
    "url": "/posts/{id}",
    "title": "Update Post",
    "name": "UpdatePost",
    "group": "Posts",
    "version": "1.0.0",
    "description": "<p>This request updates an existing post using the json body provided.</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "id",
            "description": "<p>The id of the Post</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Edit Post Description Example:",
          "content": "{\n     \"description\" : \"Edited description\"\n}",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "Success 2xx": [
          {
            "group": "Success 2xx",
            "optional": false,
            "field": "201",
            "description": "<p>Post Edited</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Success-Response:",
          "content": "HTTP/1.1 201 Edited\n{\n     \"status\" : 201,\n     \"message\" : \"Post was successfully edited\"\n}",
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
            "field": "404",
            "description": "<p>Post Not Found</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "400",
            "description": "<p>Bad Request</p>"
          }
        ],
        "Error 5xx": [
          {
            "group": "Error 5xx",
            "optional": false,
            "field": "500",
            "description": "<p>Internal Server Error</p>"
          }
        ]
      }
    },
    "filename": "src/main/java/dk/kea/soundsup/PostEndpoint.java",
    "groupTitle": "Posts"
  },
  {
    "type": "get",
    "url": "/users",
    "title": "Get all users",
    "name": "GetAllUsers",
    "group": "User",
    "version": "1.0.0",
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
            "field": "name",
            "description": "<p>First and last name of the user.</p>"
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
          "content": "{\n     \"id\": 0,\n     \"name\": \"Cristiana Man\",\n     \"email\": \"cma@mail.com\"\n},\n{\n     \"id\": 1,\n     \"name\": \"Andrei Atanasiu\",\n     \"email\": \"aa@mail.dk\"\n}",
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
    "type": "get",
    "url": "/users/{id}",
    "title": "Get user",
    "name": "getUserById",
    "group": "User",
    "version": "1.0.0",
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
            "field": "name",
            "description": "<p>First and last name of the user.</p>"
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
          "content": "{\n     \"id\": 3,\n     \"name\": \"Bat Man\",\n     \"email\": \"batman@super.org\";\n}",
          "type": "json"
        }
      ]
    },
    "error": {
      "examples": [
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 400 Bad Request\n{\n     \"error\": \"User not found\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "src/main/java/dk/kea/soundsup/UserEndpoint.java",
    "groupTitle": "User"
  }
] });
