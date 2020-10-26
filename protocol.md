# Supported Server Requests

## Square (test request)
- This request is intended to be used for testing purposes, usually to verify that communication has been established.

Message Template:
* "process": "square"
* "number": "(integer to square)"

Return Message Template 1:
* "success": "true"

Return Message Template 2:
* "success": "false"
* "reason": "(reason for failure)"

## Register
- This request registers a new user using their nickname, password and email.

Message Template:
* "process": "register"
* "email": "example@gmail.com"
* "nickname": "examplenick"
* "password": "examplepass"

Return Message Template 1:
* "success": "true"

Return Message Template 2:
* "success": "false"
* "reason": "(reason for failure)"

## Unregister
- This request unregisters a user using their nickname.

Message Template:
* "process": "unregister"
* "nickname": "examplenick"

Return Message Template 1:
* "success": "true"

Return Message Template 2:
* "success": "false"
* "reason": "(reason for failure)"

## Login
- This request logs in a user using their nickname and password.

Message Template:
* "process": "login"
* "nickname": "examplenick"
* "password": "examplepass"

Return Message Template 1:
* "success": "true"

Return Message Template 2:
* "success": "false"
* "reason": "(reason for failure)"