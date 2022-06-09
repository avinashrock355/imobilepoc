

class ApiError{
   int statusCode;
   String message;
   Exception exception;
  ApiError.name(this.statusCode,this.message,this.exception);
}