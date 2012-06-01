package com.continuuity.data.operation.ttqueue.internal;

import com.google.common.base.Objects;


/**
 * Meta data about a queue entry.
 */
public class EntryMeta {

  private final EntryState state;

  public EntryMeta(final EntryState state) {
    this.state = state;
  }

  public boolean isValid() {
    return this.state == EntryState.VALID;
  }

  public boolean isInvalid() {
    return this.state == EntryState.INVALID;
  }

  public boolean iEndOfShard() {
    return this.state == EntryState.SHARD_END;
  }

  public byte [] getBytes() {
    return this.state.getBytes();
  }

  public static EntryMeta fromBytes(byte [] bytes) {
    return new EntryMeta(EntryState.fromBytes(bytes));
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(this)
        .add("state", this.state)
        .toString();
  }

  public static enum EntryState {
    VALID, INVALID, SHARD_END;

    private static final byte [] VALID_BYTES = new byte [] { 0 };
    private static final byte [] INVALID_BYTES = new byte [] { 1 };
    private static final byte [] SHARD_END_BYTES = new byte [] { 2 };

    public byte [] getBytes() {
      switch (this) {
        case VALID:     return VALID_BYTES;
        case INVALID:   return INVALID_BYTES;
        case SHARD_END: return SHARD_END_BYTES;
      }
      return null;
    }

    public static EntryState fromBytes(byte [] bytes) {
      if (bytes.length == 1) {
        if (bytes[0] == VALID_BYTES[0]) return VALID;
        if (bytes[0] == INVALID_BYTES[0]) return INVALID;
        if (bytes[0] == SHARD_END_BYTES[0]) return SHARD_END;
      }
      throw new RuntimeException("Invalid deserialization of EntryState");
    }

    @Override
    public String toString() {
      return Objects.toStringHelper(this)
          .add("state", this.name())
          .toString();
    }
  }
}
