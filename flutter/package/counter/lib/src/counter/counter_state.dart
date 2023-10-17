part of 'counter_cubit.dart';

sealed class CounterState extends Equatable {
  const CounterState(this.count);

  final int count;

  @override
  List<Object> get props => [count];
}

final class CounterInitial extends CounterState {
  const CounterInitial() : super(0);
}

final class CounterInProgress extends CounterState {
  const CounterInProgress(super.count);
}
