package br.edu.ifpb.notificacaosinan.utils;


public sealed interface Result<T, E> permits Result.Ok, Result.Error {
    record Ok<T, E>(T value) implements Result<T, E> {}
    record Error<T, E>(E error) implements Result<T, E> {}
}
