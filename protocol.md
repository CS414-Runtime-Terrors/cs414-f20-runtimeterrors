# Supported Server Requests

## Square (test request)
- This request is intended to be used for testing purposes, usually to verify that communication has been established.

Message Template:
* "process": "square"
* "number": "(integer to square)"

Return Message Template:
* "success": "true" or "false"
* if false -> "reason": "(reason for failure")

## Register
- This request registers a new user using their nickname, password and email.

Message Template:
* process: register
* email: example@gmail.com
* nickname: examplenick
* password: examplepass

Return Message Template:
* "success": "true" or "false"
* if false -> "reason": "(reason for failure")